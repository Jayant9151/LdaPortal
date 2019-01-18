
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view >
		<center>
			<f:loadBundle var="msgBundle"
				basename="com.mentor.jboss.home.nl.homepagemsg" />
			<h:form id="form1" width="100%">
				<table width="100%" >
					<tr>
						<td>
							<table width="100%" >
							
								<tr>
										<td><a4j:outputPanel id="panelMessages">

										<h:messages infoStyle="color:green;" errorStyle="color:red;"
											style="font-weight:bold; "></h:messages>
									</a4j:outputPanel></td>
									
									</tr>
									<tr>
									<td width="100%" align="center" >
										<table width="100%">
											<tr>
											<td>
											<h:outputText value="File Description" styleClass="generalExciseStyle"></h:outputText>
											<h:inputTextarea value="#{downloadAction.fileNm }" style="height: 30px"></h:inputTextarea>
											</td>
											<td>
											<h:outputText value="Upload date" styleClass="generalExciseStyle"></h:outputText>
											<rich:calendar value="#{downloadAction.uploadDate }"></rich:calendar>
											
											</td>
											<td>
											<h:outputText value="Valid Till date" styleClass="generalExciseStyle"></h:outputText>
											<rich:calendar value="#{downloadAction.validDate }"></rich:calendar>
											
											</td>
											<td>
											<rich:fileUpload
												
												addControlLabel="Add File" id="link3"
												fileUploadListener="#{downloadAction.zipUpload}"
												maxFilesQuantity="1" listHeight="40" listWidth="250"
												clearControlLabel="clear" stopControlLabel="Stop"
												ontyperejected="if (!confirm(' ONLY .ZIP,.apk,.pdf ALLOWED !!!')) return false"
												acceptedTypes="zip,pdf,apk" clearAllControlLabel="Clear" ></rich:fileUpload>
											</td>
											</tr>
											
											<tr><td></td>
											<td align="center">
											<h:commandButton value="Save" action="#{downloadAction.save}"></h:commandButton>
											<h:commandButton value="Reset" action="#{downloadAction.reset}"></h:commandButton>
											</td>
											<td></td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
								
								
							</table>
						</td>
					</tr>
				</table>
			</h:form>
		</center>
	</f:view>
</ui:composition>