
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
			
		
				<table width="100%" align="center" >
					<tr>
						<td width="100%" align="center" >
							<table width="50%" align="center"  class="newsdiv">
							
								<tr>
									<td></td>
									<td width="1000%" align="center" >
										<table >
										
										<tr style=" height : 30px;"><td align="center" >
																				<h:commandButton value="Get OTP"
																					rendered="#{!oTPAction.otpflg}"
																					action="#{oTPAction.otpmethod1}"
																					
																				style="BACKGROUND-COLOR: #0695ea; COLOR: #ffffff; FONT-WEIGHT: bold; FONT-SIZE: large;"></h:commandButton>
																			</td></tr>
											
											<tr><td align="center">
																				<rich:spacer height="20px"></rich:spacer></td></tr>
											<tr>
											<td >
											<h:outputText rendered="#{!oTPAction.otpflg}"
																			value="Enter OTP "
																			style="FONT-SIZE: small; color: maroon; FONT-WEIGHT: bold;"/>
																			<h:inputText rendered="#{!oTPAction.otpflg}"
																			value="#{oTPAction.otpinput}"
																			style="FONT-SIZE: small; color: maroon; FONT-WEIGHT: bold;"/>
											</td>
											</tr>
												<tr><td align="center">
																				<rich:spacer height="20px"></rich:spacer></td></tr>
											
											<tr>
											<td style="FONT-WEIGHT: bold;"> <h:messages styleClass="generalExciseStyle"
			id="messages2" errorStyle="color: RED" layout="table"
			infoStyle="color:green"></h:messages></td></tr>
			
											<tr><td align="center">
																				<h:commandButton value="VERIFY"
																					rendered="#{!oTPAction.otpflg}"
																					action="#{oTPAction.otpmethod}"
																					style="BACKGROUND-COLOR: #0695ea; COLOR: #ffffff; FONT-WEIGHT: bold; FONT-SIZE: large;"
																				></h:commandButton>
																			</td></tr>
										</table>
									</td>
									
								</tr>
								<tr>
						<td  align="center"><h:panelGroup
								rendered="#{oTPAction.otpflg}">
								<table width="50%" align="center">
									
									<tr>
										<td align="center" valign="top"><h:dataTable
												styleClass="h-table" rowClasses="h-table-cell,h-table-cell"
												id="table6" rows="50"
												value="#{oTPAction.menuLinkListotp}" var="list">
												<rich:column style="min-width:230px; min-height: 220px;"
													width="230" >
													<h:panelGroup rendered="#{list.cellRenderotp1}">
														<div style="min-width: 225px; min-height: 220px;"
															align="center">
															<h:outputLink value="/#{list.linkURLotp1}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIconotp1}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												
												
												
												
												
												

											</h:dataTable></td>
									</tr>
								</table>
							</h:panelGroup></td>
					</tr>
								
							</table>
						</td>
					</tr>
				</table>
			</h:form>
		</center>
	</f:view>
</ui:composition>