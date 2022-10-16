package br.com.investidortools.report.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Service
public class AzureService {
	
	@Value(value = "${url.blob.azure}")
	private String urlBlobAzure;
	
	@Value(value = "${container.blob.azure}")
	private String container;
	
	@Value(value = "${token.blob.azure}")
	private String token;

	public ByteArrayOutputStream downloadReport(String name) {
		try {
			BlobServiceClient storageClient = new BlobServiceClientBuilder().endpoint(urlBlobAzure).sasToken(token).buildClient();
			
			BlobContainerClient blobContainerClient = storageClient.getBlobContainerClient(container);

			BlobClient blobClient = blobContainerClient.getBlobClient(name);
			
			int dataSize = (int) blobClient.getProperties().getBlobSize();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(dataSize);
			blobClient.downloadStream(outputStream);
			outputStream.close();
			return outputStream;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
