
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
			<h:form id="form1">
				<table width="100%" style="background: ;">
					
							
								
											<tr>
											<td  width="100%" align="center">
											<rich:dataTable  id="table1" rows="15" width="100%" value="#{downloadAction.displaydatalist }" var="list"
						headerClass="TableHead" footerClass="TableHead" rowClasses="TableRow1,TableRow2" >
						
						<h:column>
								<f:facet name="header">
								<h:outputText value="Sr.No." />	
								</f:facet>
								<h:outputText value="#{list.srno}" 
									style="FONT-WEIGHT: bold;"></h:outputText>
							</h:column>
										<rich:column  filterEvent="onkeyup"
								sortBy="#{list.name}">
											<f:facet name="header">
												<h:outputText value="File Description" />	
											</f:facet>
												<h:outputText value="#{list.name }" style="FONT-WEIGHT: bold;"></h:outputText>
										</rich:column>
										
										<rich:column >
											<f:facet name="header">
												
											</f:facet>
											<h:outputLink target="_self"  value="/doc/LDA/welcome/homepage/DOWNLOADS/#{list.srNO1}/#{list.filename}" >
													<h:outputText value="download " />
													<a4j:support event="onclick"
																		actionListener="#{downloadAction.listener2 }"
																		></a4j:support>
												</h:outputLink>
												 
																	
										</rich:column>
										
										
										
										
										
										
											<f:facet name="footer">
												<rich:datascroller for="table1"></rich:datascroller>
												</f:facet>
									</rich:dataTable>
											</td>
											</tr>
											 <tr><td align="center">
	         <a target="_self" href="/portal/home" >  
					<h:graphicImage 
																				 width="90"
																					value="/images/back.png"></h:graphicImage>
			</a>
	         
	         </td></tr>
											
										</table>
									
						
			</h:form>
		</center>
	</f:view>
</ui:composition>