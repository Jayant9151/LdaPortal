
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
				<table width="100%" style="background: #F3F1ED;">
					<tr>
						<td>
							<table width="100%" >
							
								<tr>
									 
									<td width="100%" align="center" >
										<iframe width="100%" height="750px"
																						src="#{customHomeAction.imagepdf}"></iframe>
									</td>
									 
								</tr>
								
								
							</table>
						</td>
					</tr>
				</table>
			</h:form>
		</center>
	</f:view>
</ui:composition>