package com.openfinance.api.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openfinance.api.dto.*;
import com.openfinance.api.entity.*;
import com.openfinance.api.repository.InvoiceFinanceRepository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ApiService {
  private static final String INVOICE_BUSINESS = "opendata-invoicefinancings_business-invoice-financings";
  private static final String INVOICE_PERSONAL = "opendata-invoicefinancings_personal-invoice-financings";
  private List<String> listEndpoint;
  @Autowired
  InvoiceFinanceRepository invoiceFinanceRepository;

  SSLContext sslContext = new SSLContextBuilder()
      .loadTrustMaterial(null, (certificate, authType) -> true).build();
  SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
      sslContext, NoopHostnameVerifier.INSTANCE);

  public ApiService() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
  }

  public static ParticipantDTO[] getHttpConn(String url) {

    try (CloseableHttpClient client = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(url);
      org.apache.http.HttpResponse response = client.execute(httpGet);
      String jsonResponse = EntityUtils.toString(response.getEntity());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper.readValue(jsonResponse, ParticipantDTO[].class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public InvoiceFinanceData accessDirectoryAndReturnFilteredJson(String url) {
    listEndpoint = new ArrayList<>();
    ParticipantDTO[] participantDTO = getHttpConn(url);
    List<ParticipantDTO.ApiDiscoveryEndpoint> endpointsBusiness = new ArrayList<>();
    List<ParticipantDTO.ApiDiscoveryEndpoint> endpointsPersonal = new ArrayList<>();
    List<InvoiceFinanceRateDTO> invoiceFinanceRateDTOPersonal = new ArrayList<>();
    List<InvoiceFinanceRateDTO> invoiceFinanceRateDTOBusiness = new ArrayList<>();

    extractedEndpoint(participantDTO, endpointsBusiness, endpointsPersonal);
    extractedData(endpointsPersonal, invoiceFinanceRateDTOPersonal);
    extractedData(endpointsBusiness, invoiceFinanceRateDTOBusiness);

    InvoiceFinanceData invoiceFinanceData = new InvoiceFinanceData();
    invoiceFinanceData.setInvoiceFinancePersonal(invoiceFinanceRateDTOPersonal);
    invoiceFinanceData.setInvoiceFinancePersonal(invoiceFinanceRateDTOBusiness);

    return invoiceFinanceData;
  }

  private static void extractedEndpoint(ParticipantDTO[] participantDTO, List<ParticipantDTO.ApiDiscoveryEndpoint> endpointsBusiness, List<ParticipantDTO.ApiDiscoveryEndpoint> endpointsPersonal) {
    if (!Objects.isNull(participantDTO) && participantDTO.length > 0)

      Arrays.stream(participantDTO).forEach(api -> {

        for (ParticipantDTO.AuthorisationServers item : api.getAuthorisationServers()) {

          Optional<ParticipantDTO.ApiResources> apiResourceBusiness = item.getApiResources().stream().filter(apiResources ->
              Objects.equals(INVOICE_BUSINESS, apiResources.apiFamilyType)).findAny();

          Optional<ParticipantDTO.ApiResources> apiResourcePersonal = item.getApiResources().stream().filter(apiResources ->
              Objects.equals(INVOICE_PERSONAL, apiResources.apiFamilyType)).findAny();

          apiResourceBusiness.ifPresent(apiResources -> {
            Optional<ParticipantDTO.ApiDiscoveryEndpoint> apiDiscoveryEndpoint = apiResources.getApiDiscoveryEndpoints().stream().findAny();
            apiDiscoveryEndpoint.ifPresent(endpointsBusiness::add);
          });

          apiResourcePersonal.ifPresent(apiResources -> {
            Optional<ParticipantDTO.ApiDiscoveryEndpoint> apiDiscoveryEndpoint = apiResources.getApiDiscoveryEndpoints().stream().findAny();
            apiDiscoveryEndpoint.ifPresent(endpointsPersonal::add);
          });
        }

      });
  }

  private void extractedData(List<ParticipantDTO.ApiDiscoveryEndpoint> endpoints, List<InvoiceFinanceRateDTO> invoiceFinanceRateDTOBusiness) {
    for (ParticipantDTO.ApiDiscoveryEndpoint api: endpoints) {
      if (!listEndpoint.contains(api.apiEndpoint)) {
        listEndpoint.add(api.apiEndpoint);
        InvoiceFinanceDTO invoiceFinanceDTO = getHttpEndpoint(api.apiEndpoint);
        InvoiceFinanceEntity invoiceFinance = new InvoiceFinanceEntity();
        BeanUtils.copyProperties(invoiceFinanceDTO, invoiceFinance);
        for (DataItem item : invoiceFinanceDTO.getData()) {
          DataItemEntity dataItemEntity = new DataItemEntity();
          ParticipantEntity participant = new ParticipantEntity();
          FeesEntity feesEntity = new FeesEntity();
          BeanUtils.copyProperties(item, dataItemEntity);
          List<InterestRateEntity> interestRateEntityList = new ArrayList<>();

          for (InterestRate obj : item.getInterestRates()) {
            InterestRateEntity interestRateEntity1 = new InterestRateEntity();
            BeanUtils.copyProperties(obj, interestRateEntity1);
            interestRateEntityList.add(interestRateEntity1);
            InvoiceFinanceRateDTO invoiceFinanceRateDTO = new InvoiceFinanceRateDTO();
            invoiceFinanceRateDTO.setRate(obj.getRate());
            invoiceFinanceRateDTO.setParticipant(item.getParticipant().getBrand());
            invoiceFinanceRateDTO.setReferentialRateIndexer(obj.getReferentialRateIndexer());
            invoiceFinanceRateDTO.setMinimum(obj.getMinimumRate());
            invoiceFinanceRateDTO.setType(item.getType());
            invoiceFinanceRateDTO.setMaximum(obj.getMaximumRate());
            invoiceFinanceRateDTOBusiness.add(invoiceFinanceRateDTO);
          }

          BeanUtils.copyProperties(item.getFees(), feesEntity);
          BeanUtils.copyProperties(item.getParticipant(), participant);
          invoiceFinance.getData().add(dataItemEntity);
          dataItemEntity.setParticipant(participant);
          dataItemEntity.setFees(feesEntity);
          dataItemEntity.setInterestRates(interestRateEntityList);

        }
        invoiceFinanceRepository.save(invoiceFinance);
      }
    }
  }


  public InvoiceFinanceDTO getHttpEndpoint(String url) {
    try (CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(this.sslsf).build()) {
      HttpGet httpGet = new HttpGet(url);
      org.apache.http.HttpResponse response = client.execute(httpGet);
      String jsonResponse = EntityUtils.toString(response.getEntity());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      System.out.println(jsonResponse);
      client.close();
      return mapper.readValue(jsonResponse, InvoiceFinanceDTO.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
