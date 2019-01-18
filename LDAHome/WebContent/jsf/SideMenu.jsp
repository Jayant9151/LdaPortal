
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">


	<table>
		<tr>
			<td></td>
			<td>

				<table>
					<tr>
						<td><h:panelGroup>
								<table>
									<tr>
										<td><h:outputText value=" "></h:outputText> </td>
									</tr>
									<tr>
										<td><h:dataTable styleClass="h-table"
												rowClasses="h-table-cell,h-table-cell" id="table6" rows="50"
												value="#{sideMenuAction.subMenuList}" var="list">
												<rich:column>
													<h:outputLink value="/#{list.linkURL}">
														<h:outputText value="#{list.linkName}" />
													</h:outputLink>
												</rich:column>
											</h:dataTable></td>
									</tr>
								</table>
							</h:panelGroup></td>
					</tr>
					<tr>
						<td><h:panelGroup rendered="false">
								<table>
									<tr>
										<td><h:outputText value="PROCESS"></h:outputText></td>
									</tr>
									<tr>
										<td><h:dataTable styleClass="h-table"
												rowClasses="h-table-cell,h-table-cell" id="table6" rows="50"
												value="#{sideMenuAction.subMenuList}" var="list">
												<rich:column>
													<h:outputLink value="/#{list.linkURL}">
														<h:outputText value="#{list.linkName}" />
													</h:outputLink>
												</rich:column>
											</h:dataTable></td>
									</tr>
								</table>
							</h:panelGroup></td>
					</tr>
					<tr>
						<td><h:panelGroup rendered="false">
								<table>
									<tr>
										<td><h:outputText value="REPORTS"></h:outputText></td>
									</tr>
									<tr>
										<td><h:dataTable styleClass="h-table"
												rowClasses="h-table-cell,h-table-cell" id="table6" rows="50"
												value="#{sideMenuAction.subMenuList}" var="list">
												<rich:column>
													<h:outputLink value="/#{list.linkURL}">
														<h:outputText value="#{list.linkName}" />
													</h:outputLink>
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
</ui:composition>