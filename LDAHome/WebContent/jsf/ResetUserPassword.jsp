
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>

		<center>
			<f:loadBundle var="msgBundle"
				basename="com.mentor.jboss.home.nl.homepagemsg" />
			<h:form id="form1">
				<table width="100%">
				<tr>
							<td colspan="3"><h:messages  infoStyle="color:green; font-size:14px;" errorStyle="color:red; font-size:14px;"></h:messages> </td>
							<td></td>
							<td></td>
							</tr>
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td></td>
									<td width="100%" align="center">
									
										<table>
											<tr>
												<td height="50"><h:outputText styleClass="generalExciseStyle"  value="Section Name"></h:outputText></td>
												<td><h:selectOneMenu styleClass="FormContentSize"
														value="#{userManagmentAction.sectionIDForReset}"
														onchange="this.form.submit();"  style=" width : 180px;" 
														valueChangeListener="#{userManagmentAction.changeListnerReset}">
														<f:selectItems value="#{userManagmentAction.sectionList}" />
													</h:selectOneMenu></td>
												<td></td>
												<td><h:outputText  styleClass="generalExciseStyle"  value="Employee Name"></h:outputText></td>
												<td><h:selectOneMenu styleClass="FormContentSize"  style=" width : 180px;" 
														value="#{userManagmentAction.empIDForReset}">
														<f:selectItems value="#{userManagmentAction.empList}" />
													</h:selectOneMenu></td>
												<td></td>
											</tr>
											
										</table>
									
									</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td align="center" height="50">
										<table>
											<tr>
												<td colspan="2"><h:outputLabel value=" Note* : Your new password will be sent to your Mobile Number."
														style="COLOR: #ff0000;">
													</h:outputLabel></td>
												
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td align="center" height="50">
										<table>
											<tr>
												<td><h:commandButton value=" Generate New Password "
														action="#{userManagmentAction.saveResetPassword}">
													</h:commandButton></td>
												<td><h:commandButton value=" Reset "
														action="#{userManagmentAction.resetData}">
													</h:commandButton></td>
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