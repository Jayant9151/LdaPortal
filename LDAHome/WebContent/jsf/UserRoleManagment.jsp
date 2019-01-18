
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
						<td>
							<table width="100%">
								<tr>
									<td></td>
									<td align="center" height="50"><h:outputText
											style="FONT-SIZE: large; FONT-WEIGHT: bold;"
											value="User Role Managment"></h:outputText></td>
									<td></td>
								</tr>
								<tr>
							<td colspan="3"><a4j:outputPanel id="errorMsg"><h:messages  infoStyle="color:green; font-size:14px;" errorStyle="color:red; font-size:14px;"></h:messages></a4j:outputPanel> </td>
							<td></td>
							<td></td>
							</tr><tr>
							<td colspan="4" align="center">
							<h:selectOneRadio styleClass="generalExciseStyle" onchange="this.form.submit();"
															value="#{userManagmentAction.radio}"  >
															<f:selectItem itemValue="E" itemLabel="Filter on Employee " />
															<f:selectItem itemValue="M" itemLabel="Filter on Module" />
														</h:selectOneRadio>
							 </td>
							
							</tr>
							
							
							<tr><td width="100%" align="center" colspan="4"><h:panelGroup rendered="#{userManagmentAction.radio eq 'E'}">
							<table  width="100%" align="center">
							<tr>
									<td></td>
									<td width="100%" align="center">
										<table>
											<tr>
												<td><h:outputText styleClass="generalExciseStyle"
														value="Section Name" rendered="#{!userManagmentAction.newUser}"></h:outputText></td>
												<td height="50"><h:selectOneMenu rendered="#{!userManagmentAction.newUser}"
														value="#{userManagmentAction.sectionIDForMgmt}"
														onchange="this.form.submit();"
														valueChangeListener="#{userManagmentAction.changeListnerMgmt}" style=" width : 180px;">
														<f:selectItems value="#{userManagmentAction.sectionList}" />
													</h:selectOneMenu></td>
												<td></td>
												<td><h:outputText styleClass="generalExciseStyle" rendered="#{!userManagmentAction.newUser}"
														value="Employee Name"></h:outputText></td>
												<td><h:selectOneMenu  rendered="#{!userManagmentAction.newUser}"
														value="#{userManagmentAction.empIDForMgmt}"
													onchange="this.form.submit();" style=" width : 180px;" 
													valueChangeListener="#{userManagmentAction.userMangeListner}">
														<f:selectItems value="#{userManagmentAction.empList}" />
													</h:selectOneMenu>
														<rich:spacer width="30px"></rich:spacer>
														<h:commandButton value="VIEW"
															action="#{userManagmentAction.viewData}" />
														</td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
							<tr>
									<td></td>
									<td width="100%" align="center">
									<a4j:outputPanel id="msg">
									<h:panelGroup  rendered="#{userManagmentAction.msgPanel}">
										<table>
											<tr>
												<td colspan="5"><h:outputLabel value="This User Does not Exists!!! " style="COLOR: #ff0000; FONT-SIZE: large; FONT-WEIGHT: bold;"></h:outputLabel></td>
												<td colspan="1">
												<a4j:commandButton value=" Create New User "  oncomplete="#{rich:component('ClassPanel1')}.show()"
												 actionListener="#{userManagmentAction.newUserListener}">
														
												</a4j:commandButton></td>
											</tr>
											
											
										</table></h:panelGroup></a4j:outputPanel>
									</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td align="center">
									<a4j:outputPanel id="tab">
									<h:panelGroup rendered="#{!userManagmentAction.newUser}">
									<h:panelGrid columns="1"
											style="max-width:600px;" width="100%" >
											<f:facet name="header">
												<h:outputText value="List of Available Modules"
													style="FONT-SIZE: medium; FONT-WEIGHT: bold;" />
											</f:facet>
										</h:panelGrid>
										<div
											style="max-width: 600px; max-height: 310px; overflow: auto;">
											<rich:dataTable width="100%" id="roleAssignTab" var="list"
												headerClass="TableHead" rows="30"
												value="#{userManagmentAction.moduleList}"
												footerClass="TableHead" rowClasses="TableRow1,TableRow2">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Serial No."
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.serailNo}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Modules Name"
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.moduleName}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
															value="" />
													</f:facet>
													<h:selectBooleanCheckbox value="#{list.check}" >
													<a4j:support event="onclick" reRender="rllst" actionListener="#{userManagmentAction.showDedList}"/>
													</h:selectBooleanCheckbox>
												</rich:column>
												
												<f:facet name="footer">
													<rich:datascroller for="roleAssignTab">
													</rich:datascroller>
												</f:facet>
											</rich:dataTable>
										</div></h:panelGroup> 
										
										
										
										
										</a4j:outputPanel></td>
									<td></td>
								</tr>
								
								<tr><td  align="center" colspan="3"><a4j:outputPanel id ="rllst"> <h:panelGroup rendered="#{!userManagmentAction.newUser and userManagmentAction.roleflg}">
									<h:panelGrid columns="1"
											style="max-width:600px;" width="100%" >
											<f:facet name="header">
												<h:outputText value="List of Available Roles"
													style="FONT-SIZE: medium; FONT-WEIGHT: bold;" />
											</f:facet>
										</h:panelGrid>
										<div
											style="max-width: 600px; max-height: 310px; overflow: auto;">
											<rich:dataTable width="100%" id="roleeAssignTab" var="list"
												headerClass="TableHead" rows="30"
												value="#{userManagmentAction.roleList}"
												footerClass="TableHead" rowClasses="TableRow1,TableRow2">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Serial No."
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.serailNorole}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Modules Name"
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.roleName}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
															value="" />
													</f:facet>
													<h:selectBooleanCheckbox value="#{list.rolecheck}" >
													<a4j:support event="onclick" reRender="dedlst" actionListener="#{userManagmentAction.showDedList}"/>
													</h:selectBooleanCheckbox>
												</rich:column>
												
												<f:facet name="footer">
													<rich:datascroller for="roleeAssignTab">
													</rich:datascroller>
												</f:facet>
											</rich:dataTable>
										</div></h:panelGroup></a4j:outputPanel></td></tr>
								<tr>
									<td></td>
									<td align="center"><a4j:outputPanel id="create"><h:panelGroup rendered="#{userManagmentAction.newUser}">
									
										</h:panelGroup></a4j:outputPanel></td>
									<td></td>
								</tr>
								
								<tr>
									<td></td>
									<td align="center" height="50">	<a4j:outputPanel id="buttonsave"><h:panelGroup rendered="#{!userManagmentAction.newUser}">
										<table>
											<tr>
												<td><h:commandButton value="Save" reRender="msg,errorMsg"
														action="#{userManagmentAction.saveRoleMgmt}" /></td>
												<td><h:commandButton value="Reset"
														action="#{userManagmentAction.resetData}" /></td>
											</tr>
										</table></h:panelGroup></a4j:outputPanel>
									</td>
									<td></td>
								</tr>
							</table></h:panelGroup></td></tr>
								
								
							<tr><td width="100%" align="center" colspan="4"><h:panelGroup rendered="#{userManagmentAction.radio eq 'M'}">
							<table  width="100%" align="center">
							<tr>
									<td></td>
									<td width="100%" align="center">
										<table>
											<tr>
												<td><h:outputText styleClass="generalExciseStyle"
														value="Module :"></h:outputText></td>
												<td height="50"><h:selectOneMenu onchange="this.form.submit()"
														value="#{userManagmentAction.modulid}"
														 style=" width : 180px;">
														<f:selectItems value="#{userManagmentAction.modulList}" />
													</h:selectOneMenu></td>
												<td></td>
												<td><h:outputText styleClass="generalExciseStyle" 
														value="Roles :"></h:outputText></td>
												<td><h:selectOneMenu  
														value="#{userManagmentAction.roleid}"
													 style=" width : 180px;" >
														<f:selectItems value="#{userManagmentAction.roleidList}" />
													</h:selectOneMenu>
														<rich:spacer width="30px"></rich:spacer>
														<h:commandButton value="VIEW"
															action="#{userManagmentAction.viewData2}" />
														</td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
							
								<tr>
									<td></td>
									<td align="center">
									
									<h:panelGroup >
									<h:panelGrid columns="1"
											style="max-width:600px;" width="100%" >
											<f:facet name="header">
												<h:outputText value="List of Available Employees"
													style="FONT-SIZE: medium; FONT-WEIGHT: bold;" />
											</f:facet>
										</h:panelGrid>
										<div
											style="max-width: 600px; max-height: 310px; overflow: auto;">
											<rich:dataTable width="100%" id="roleempTab" var="list"
												headerClass="TableHead" 
												value="#{userManagmentAction.emprolList}"
												footerClass="TableHead" rowClasses="TableRow1,TableRow2">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Serial No."
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.serailNo}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Employee Name"
															style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
													</f:facet>
													<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
														value="#{list.usrnm}" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
															value="" />
													</f:facet>
													<h:selectBooleanCheckbox value="#{list.usrcheck}" >
													
													</h:selectBooleanCheckbox>
												</rich:column>
												
												<f:facet name="footer">
													
												</f:facet>
											</rich:dataTable>
										</div></h:panelGroup> 
										
										
										
										
										</td>
									<td></td>
								</tr>
								
								
								
								
								<tr>
									<td></td>
									<td align="center" height="50">	
										<table>
											<tr>
												<td><h:commandButton value="Save" reRender="msg,errorMsg"
														action="#{userManagmentAction.save1}" /></td>
												<td><h:commandButton value="Reset"
														action="#{userManagmentAction.resetData}" /></td>
											</tr>
											<tr>
												<td><h:outputText styleClass="generalExciseStyle"
														value="Employee Id-Name" ></h:outputText></td>
												<td height="50"><h:selectOneMenu 
														value="#{userManagmentAction.empid}"
														onchange="this.form.submit();"
														 style=" width : 180px;">
														<f:selectItems value="#{userManagmentAction.empidList}" />
													</h:selectOneMenu></td>
												<td></td>
												<td><h:outputText styleClass="generalExciseStyle" 
														value="Employee Name-Id."></h:outputText></td>
												<td><h:selectOneMenu 
														value="#{userManagmentAction.empnm}"
													onchange="this.form.submit();" style=" width : 180px;" 
													 >
														<f:selectItems value="#{userManagmentAction.empnmList}" />
													</h:selectOneMenu>
														<rich:spacer width="30px"></rich:spacer>
														<h:commandButton value="Add"
															action="#{userManagmentAction.save2}" />
														</td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
							</table></h:panelGroup></td></tr>	
								
								
								
								
								
								
							</table>
						</td>
					</tr>
				</table>
			</h:form>
			<rich:modalPanel id="ClassPanel1" minWidth="700" autosized="true"  style="border: navy;background-color:silver; ">
	
    	    <f:facet name="header">
	            <h:panelGroup> <h:outputText value="Create User" /> </h:panelGroup>
        	</f:facet>

        <h:form id="editClassForm1">
        
        <a4j:outputPanel id="errorMessage"><h:messages  infoStyle="color:green; font-size:14px;" errorStyle="color:red; font-size:14px;" style="FONT-WEIGHT: bold;"></h:messages></a4j:outputPanel>
       		 
       		<table><tr><td align="center" width="80%"><table align="center" ><f:facet name="header">
												<h:outputText value="Create New User Account"
													style="FONT-SIZE: small; FONT-WEIGHT: bold;" />
											</f:facet>
											<tr><td ><rich:spacer height="20%"></rich:spacer></td></tr>
											<tr><td align="left"><h:outputText value="Username *" style="FONT-WEIGHT: bold;"/></td><td><h:inputText value="#{userManagmentAction.userid }" disabled="true" /> </td></tr>
													<tr><td align="left"><h:outputText value="E-Mail *" style="FONT-WEIGHT: bold;"/></td><td><h:inputText value="#{userManagmentAction.email }" /> </td></tr>
													<tr><td align="left"><h:outputText value="Password *" style="FONT-WEIGHT: bold;"/></td><td><h:inputSecret value="#{userManagmentAction.password }" /> </td></tr>
													<tr><td align="left"><h:outputText value="Confirm Password *" style="FONT-WEIGHT: bold;"/></td><td><h:inputSecret value="#{userManagmentAction.repassword }" /> </td></tr>
													<tr><td align="left"><h:outputText value="* indicates required field." style="FONT-WEIGHT: bold;"/></td><td></td></tr>
													<tr><td><rich:spacer height="20"></rich:spacer> </td><td></td></tr>
													<tr><td>
													<a4j:commandButton value="Submit"	action="#{userManagmentAction.saveUser}" reRender="errorMessage"></a4j:commandButton>
													<a4j:commandButton   value="Clear" action="#{userManagmentAction.clear}"  reRender="editClassForm1" 
													oncomplete="if (document.getElementById('hasMessages').value=='false') Richfaces.hideModalPanel('ClassPanel1');" ajaxSingle="true"  />
													<h:commandButton value="Close"
														action="#{userManagmentAction.resetData}" onclick="this.form.submit();" /></td>
													</tr>
													</table></td>
        	</tr>
        	
        	
        	
        	</table>
        		
									
		</h:form>
    	</rich:modalPanel>
		</center>
	</f:view>
</ui:composition>