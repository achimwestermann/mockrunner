package com.github.kristofa.test.http.file;

import java.io.File;

import com.github.kristofa.test.http.HttpResponse;
import com.github.kristofa.test.http.HttpResponseProxy;

public class FileHttpResponseProxy implements HttpResponseProxy {

	private final String directory;
	private final String filename;
	private final int seqNr;
	private boolean isConsumed = false;
	private boolean isNeedVerify = true;
	private final HttpResponseFileReader httpResponseFileReader;

	public FileHttpResponseProxy(final String directory, final String filename, final int seqNr,
			final HttpResponseFileReader responseFileReader) {
		this.directory = directory;
		this.filename = filename;
		this.seqNr = seqNr;
		httpResponseFileReader = responseFileReader;
	}

	@Override
	public boolean consumed() {
		return isConsumed;
	}

	@Override
	public HttpResponse getResponse() {
		return readResponse();
	}

	@Override
	public HttpResponse consume() {
		final HttpResponse response = readResponse();
		isConsumed = true;
		return response;
	}

	private HttpResponse readResponse() {
		final File responseFile = new File(directory, FileNameBuilder.RESPONSE_FILE_NAME.getFileName(filename, seqNr));
		final File responseEntityFile = new File(directory,
				FileNameBuilder.RESPONSE_ENTITY_FILE_NAME.getFileName(filename, seqNr));
		final HttpResponse response = httpResponseFileReader.read(responseFile, responseEntityFile);
		return response;
	}

	public boolean isNeedVerify() {
		return isNeedVerify;
	}

	public void setNeedVerify(boolean isNeedVerify) {
		this.isNeedVerify = isNeedVerify;
	}

}
