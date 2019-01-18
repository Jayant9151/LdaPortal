
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
									<td></td>
									<td width="100%" align="center" >
										<table>
											<tr>
											<td>
											<rich:dataTable  id="table1" rows="15" width="100%" value="#{downloadAction.displaydatalist }" var="list"
						headerClass="TableHead" footerClass="TableHead" rowClasses="TableRow1,TableRow2" >
						
						<h:column>
								<f:facet name="header">
									
								</f:facet>
								<h:outputText value="#{list.srNO1}"
									styleClass="generalExciseStyle" style="FONT-WEIGHT: bold;"></h:outputText>
							</h:column>
										<rich:column filterBy="#{list.name}" filterEvent="onkeyup"
								sortBy="#{list.name}">
											<f:facet name="header">
												
											</f:facet>
												<h:outputText value="#{list.name }" styleClass="generalExciseStyle" style="FONT-WEIGHT: bold;"></h:outputText>
										</rich:column>
										
										<rich:column >
											<f:facet name="header">
												
											</f:facet><h:commandLink 
											 <a href="/doc/LDA/welcome/homepage/DOWNLOADS/#{list.srNO1}" target="_self"></a>
																					
																					value="DOWNLOADS" >
															</h:commandLink>
										</rich:column>
										
										
										
										
										
										
											<f:facet name="footer">
												<rich:datascroller for="table1"></rich:datascroller>
												</f:facet>
									</rich:dataTable>
											</td>
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