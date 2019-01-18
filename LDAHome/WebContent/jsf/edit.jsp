<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<style type="text/css">
<!-- /** 
 * Slideshow style rules.
 */
#slideshow {
	margin: 0 auto;
	width: 600px;
	height: 325px;
	background: transparent no-repeat 0 0;
	position: relative;
}

#slideshow #slidesContainer {
	margin: 0 auto;
	width: 530px;
	height: 325px;
	overflow: auto; /* allow scrollbar */
	position: relative;
}

#slideshow #slidesContainer .slide {
	margin: 0 auto;
	width: 520px;
	/* reduce by 20 pixels of #slidesContainer to avoid horizontal scroll */
	height: 325px;
}

/** 
 * Slideshow controls style rules.
 */
.control {
	display: block;
	width: 25px;
	height: 325px;
	text-indent: -10000px;
	position: absolute;
	cursor: pointer;
}

#leftControl {
	top: 0;
	left: 0;
	background: transparent url(/doc/images/news-images/control_left.png)
		no-repeat 0 0;
}

#rightControl {
	top: 0;
	right: 0;
	background: transparent url(/doc/images/news-images/control_right.png)
		no-repeat 0 0;
}

/** 
 * Style rules for Demo page
 */
* {
	margin: 0;
	padding: 0;
	font: normal 11px Verdana, Geneva, sans-serif;
}

a {
	color: #fff;
	font-weight: bold;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

a img {
	border: 0px solid #ccc;
}

.slide h2,.slide p {
	margin: 15px;
}

.slide h2 {
	font: italic 24px Georgia, "Times New Roman", Times, serif;
	color: #ccc;
	letter-spacing: -1px;
}

.slide img {
	float: right;
	margin: 0 15px;
}
-->

</style>
		<script type="text/javascript"
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								var currentPosition = 0;
								var slideWidth = 560;
								var slides = $('.slide');
								var numberOfSlides = slides.length;

								// Remove scrollbar in JS
								$('#slidesContainer').css('overflow', 'hidden');

								// Wrap all .slides with #slideInner div
								slides.wrapAll('<div id="slideInner"></div>')
								// Float left to display horizontally, readjust .slides width
								.css({
									'float' : 'left',
									'width' : slideWidth
								});

								// Set #slideInner width equal to total width of all slides
								$('#slideInner').css('width',
										slideWidth * numberOfSlides);

								// Insert controls in the DOM
								$('#slideshow')
										.prepend(
												'<span class="control" id="leftControl">Clicking moves left</span>')
										.append(
												'<span class="control" id="rightControl">Clicking moves right</span>');

								// Hide left arrow control on first load
								manageControls(currentPosition);

								// Create event listeners for .controls clicks
								$('.control')
										.bind(
												'click',
												function() {
													// Determine new position
													currentPosition = ($(this)
															.attr('id') == 'rightControl') ? currentPosition + 1
															: currentPosition - 1;

													// Hide / show controls
													manageControls(currentPosition);
													// Move slideInner using margin-left
													$('#slideInner')
															.animate(
																	{
																		'marginLeft' : slideWidth
																				* (-currentPosition)
																	});
												});

								// manageControls: Hides and Shows controls depending on currentPosition
								function manageControls(position) {
									// Hide left arrow if position is first slide
									if (position == 0) {
										$('#leftControl').hide()
									} else {
										$('#leftControl').show()
									}
									// Hide right arrow if position is last slide
									if (position == numberOfSlides - 1) {
										$('#rightControl').hide()
									} else {
										$('#rightControl').show()
									}
								}
							});
		</script>
		<center>
			<f:loadBundle var="msgBundle"
				basename="com.mentor.jboss.home.nl.homepagemsg" />
			<h:form id="form1">
				<table width="100%">
					<tr>
						<td><h:panelGroup
								rendered="#{!customHomeAction.renderHomePage}">
								<table width="100%">
									<tr>
										<td height="15"></td>
										<h:inputHidden value="#{customHomeAction.hidn}"></h:inputHidden>
									</tr>
									<tr>
										<td align="center" valign="top"><h:dataTable
												styleClass="h-table" rowClasses="h-table-cell,h-table-cell"
												id="table6" rows="50"
												value="#{customHomeAction.menuLinkList}" var="list">
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender1}">
													<h:panelGroup rendered="#{list.cellRender1}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL1}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon1}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender2}">
													<h:panelGroup rendered="#{list.cellRender2}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL2}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon2}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender3}">
													<h:panelGroup rendered="#{list.cellRender3}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL3}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon3}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender4}">
													<h:panelGroup rendered="#{list.cellRender4}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL4}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon4}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender5}">
													<h:panelGroup rendered="#{list.cellRender5}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL5}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon5}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender6}">
													<h:panelGroup rendered="#{list.cellRender6}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL6}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon6}" />
																<br />
															</h:outputLink>
														</div>
													</h:panelGroup>
												</rich:column>
												<rich:column style="min-width:130px; min-height: 120px;"
													width="130" rendered="#{customHomeAction.columnRender7}">
													<h:panelGroup rendered="#{list.cellRender7}">
														<div style="min-width: 125px; min-height: 120px;"
															align="center">
															<h:outputLink value="/#{list.linkURL7}">
																<h:graphicImage styleClass="iconImageEffect"
																	value="#{list.linkIcon7}" />
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
					<tr>
						<td><h:panelGroup
								rendered="#{customHomeAction.renderHomePage}"
								style="width:100%;">



								<table align="center" width="100%">
									<tr>
										<td><a4j:outputPanel id="render10">
												<h:panelGroup rendered="#{customHomeAction.rendere9}">
													<table align="center" width="100%">

														<tr>

															<td height="14px"
																style="text-align: top; no-repeat; font-size: 8px; color: Black; padding-top: 0px;"
																colspan="8"><a4j:outputPanel>

																	<marquee behavior="scroll" direction="left"
																		height="14px"
																		style="margin-top: -6px; margin-bottom: 4px"
																		scrollamount="3" scrolldelay="3"
																		onmouseover="this.stop()" onmouseout="this.start()">

																		<h:outputText escape="false"
																			value="#{customHomeAction.newsTextToDisplay}"
																			style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
																	</marquee>
																</a4j:outputPanel></td>
														</tr>

														<tr>

															<td width="100%" height="33px"
																style="text-align: center; background-image: url('/doc/LDA/welcome/homepage/top/grad_img.png'); background-size: 100% 29px; background-repeat: no-repeat; font-size: 12px; color: Black; padding-top: 0px;">

																<a4j:commandLink id="E" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<h:outputText value="Establishment  | "
																		style="FONT-WEIGHT: bold;" />
																</a4j:commandLink> <a4j:commandLink id="A" onclick="true"
																	actionListener="#{customHomeAction.listener2}"
																	reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">

																	<b> <h:outputText value="Achievement  | "
																			style="FONT-WEIGHT: bold;"></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="O" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="back3,pdfrActsRules,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText
																			value="Oraganisation Structure  | "
																			style="FONT-WEIGHT: bold;"></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="MPO" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText style="FONT-WEIGHT: bold;"
																			value="Master Plan Objectives  |"></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="ZR" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="back3,pdfrActsRules,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText style="FONT-WEIGHT: bold;"
																			value="Zoning Regulation  | "></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="DS" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="back3,pdfrActsRules,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText value="Defined Standard  | "
																			style="FONT-WEIGHT: bold;"></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="MPM" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText style="FONT-WEIGHT: bold;"
																			value="Master Plan Map  | "></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="LR" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="back3,pdfrActsRules,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText style="FONT-WEIGHT: bold;"
																			value="Land Rates  | "></h:outputText>
																	</b>
																</a4j:commandLink> <a4j:commandLink id="RTI" onclick="true"
																	actionListener="#{customHomeAction.listener2 }"
																	reRender="back3,pdfrActsRules,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																	<b> <h:outputText value="RTI  "
																			style="FONT-WEIGHT: bold;"></h:outputText>
																	</b>
																</a4j:commandLink>

															</td>
														</tr>


														<tr>
															<td>
																<table align="center" width="100%">
																	<tr>
																		<td></td>
																		<td align="right"><a4j:outputPanel id="back3">
																				<h:commandButton value="Back"
																					image="/images/back.png"
																					action="#{customHomeAction.backMethod}"
																					style="border:3; background: transparent; font-size: 15px ;"
																					rendered="#{customHomeAction.backRendered}"></h:commandButton>
																			</a4j:outputPanel></td>
																	</tr>
																	<tr>

																		<td width="15%" style="min-width: 160px;" valign="top">
																			<a4j:outputPanel id="render9">
																				<h:panelGroup
																					rendered="#{customHomeAction.rendere1}">

																					<div class="newsdiv"
																						style="min-width: 160px; min-height: 258px; max-height: 258px; color: black;">
																						<table align="center" width="100%">
																							<tr>
																								<h:graphicImage height="180px" width="190px"
																									value="/images/cm.jpg"></h:graphicImage>
																							</tr>
																							<tr>
																								<td align="center" width="100%"><h:outputText
																										value="Changing horizon of the state capital under 
																	the visionary guidance of Hon'able Chief Minister Shri Akhilesh Yadav"
																										style="FONT-WEIGHT: bold; text-align: center;"></h:outputText>
																								</td>
																							</tr>
																						</table>


																					</div>
																				</h:panelGroup>
																			</a4j:outputPanel> <a4j:outputPanel id="renderActRule">
																				<h:panelGroup
																					rendered="#{customHomeAction.renderActsRules}">

																					<div class="newsdiv"
																						style="min-width: 160px; color: black; overflow: auto;">
																						<table border="1"
																							style="border-collapse: collapse;" width="100%">
																							<tr>
																								<td align="center" height="30"
																									style="FONT-SIZE: small; FONT-WEIGHT: bold; background-image: url('/doc/LDA/welcome/homepage/top/grad_img.png'); background-size: 100% 32px; background-repeat: no-repeat;">
																									Acts and Rules</td>
																							</tr>
																						</table>
																						<table border="0"
																							style="border-collapse: collapse;" width="100%">
																							<tr>
																								<td align="center" height="8"></td>
																							</tr>


																							<tr>
																								<td align="left" height="20"><a4j:commandLink
																										id="LAR" onclick="true"
																										actionListener="#{customHomeAction.listenerFrActsRules }"
																										reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																										<b> <h:outputText
																												styleClass="generalExciseStyle"
																												value="Land Acquisition Rules"
																												style="FONT-WEIGHT: bold;"></h:outputText>
																										</b>
																									</a4j:commandLink></td>
																							</tr>
																							<tr>
																								<td align="left" height="20"><a4j:commandLink
																										id="PR" onclick="true"
																										actionListener="#{customHomeAction.listenerFrActsRules }"
																										reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																										<b> <h:outputText
																												styleClass="generalExciseStyle"
																												value="Property Rules"
																												style="FONT-WEIGHT: bold;"></h:outputText>
																										</b>
																									</a4j:commandLink></td>
																							</tr>
																							<tr>
																								<td align="left" height="20"><a4j:commandLink
																										id="BR" onclick="true"
																										actionListener="#{customHomeAction.listenerFrActsRules }"
																										reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																										<b> <h:outputText
																												styleClass="generalExciseStyle"
																												value="Bhawan Rules"
																												style="FONT-WEIGHT: bold;"></h:outputText>
																										</b>
																									</a4j:commandLink></td>
																							</tr>
																							<tr>
																								<td align="left" height="20"><a4j:commandLink
																										id="RR" onclick="true"
																										actionListener="#{customHomeAction.listenerFrActsRules }"
																										reRender="pdfrActsRules,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																										<b> <h:outputText
																												styleClass="generalExciseStyle"
																												value="RBO Rules" style="FONT-WEIGHT: bold;"></h:outputText>
																										</b>
																									</a4j:commandLink></td>
																							</tr>

																						</table>
																					</div>
																				</h:panelGroup>
																			</a4j:outputPanel>

																		</td>
																		<td width="85%">
																			<table width="100%">
																				<tr>
																					<td style="min-width: 400px;" width="85%"
																						align="center"><a4j:outputPanel id="imgread">
																							<h:panelGroup
																								rendered="#{customHomeAction.imgRender}">
																								<div id="sliderFrame">

																									<div id="slider">

																										<h:graphicImage
																											value="#{customHomeAction.image1}"
																											width="100%" alt="" />
																										<h:graphicImage width="100%"
																											value="#{customHomeAction.image2}" alt="" />
																										<h:graphicImage width="100%"
																											value="#{customHomeAction.image3}" alt="" />
																										<h:graphicImage width="100%"
																											value="#{customHomeAction.image4}" alt="" />
																										<h:graphicImage width="100%"
																											value="#{customHomeAction.image5}" alt="" />
																										<h:graphicImage width="100%"
																											value="#{customHomeAction.image6}" alt="" />

																									</div>

																								</div>
																							</h:panelGroup>
																						</a4j:outputPanel></td>
																					<td style="min-width: 160px;" width="15%"
																						valign="top"><a4j:outputPanel id="render3">
																							<h:panelGroup
																								rendered="#{customHomeAction.rendere1}">

																								<div class="newsdiv"
																									style="min-width: 160px; min-height: 258px; max-height: 258px; color: black;">
																									<table width="100%">
																										<tr>
																											<h:outputLink styleClass="outputLinkEx"
																												id="linkEx3"
																												value="/doc/LDA/welcome/homepage/VISION2015.pdf"
																												target="_blank">
																												<h:graphicImage height="180px" width="190px"
																													value="/images/ldavision.jpg"></h:graphicImage>
																											</h:outputLink>
																										</tr>
																										<tr>
																											<td align="center" width="90%"><h:outputText
																													value="Address :"
																													style="TEXT-DECORATION: underline; FONT-WEIGHT: bold;" />
																											</td>
																										</tr>
																										<tr>
																											<td align="center" width="90%"><h:outputText
																													value="Pradhikaran Bhawan,"
																													style="FONT-WEIGHT: bold;" /></td>
																										</tr>
																										<tr>
																											<td align="center" width="90%"><h:outputText
																													value="Vipin Khand,Gomti Nagar,"
																													style="FONT-WEIGHT: bold;" /></td>
																										</tr>
																										<tr>
																											<td align="center" width="90%"><h:outputText
																													value="Lucknow." style="FONT-WEIGHT: bold;" />
																											</td>
																										</tr>
																										<tr>
																											<td align="center" width="90%"><h:outputText
																													value="contactuslda22@gmail.com"
																													style="FONT-WEIGHT: bold;" /></td>
																										</tr>
																									</table>
																									<table align="center" width="90%">
																										<!--  <tr align="center">
																	 <td align="center" width="90%">
																	 <h:outputText value="Address :" style="TEXT-DECORATION: underline; FONT-WEIGHT: bold;"/>
																	 </td>
																</tr>
																<tr>
																	 <td align="center" width="90%">
																	<h:outputText value="Pradhikaran Bhawan," style="FONT-WEIGHT: bold;"/>
																	 </td>
																</tr>
																<tr>
																	 <td align="center" width="90%">
																	<h:outputText value="Vipin Khand,Gomti Nagar," style="FONT-WEIGHT: bold;"/>
																	 </td>
																</tr>
																<tr>
																	 <td align="center" width="90%">
																	<h:outputText value="Lucknow." style="FONT-WEIGHT: bold;"/>
																	 </td>
																</tr>
																<tr>
																	 <td align="center" width="90%">
																	<h:outputText value="contactuslda22@gmail.com" style="FONT-WEIGHT: bold;"/>
																	 </td>
																</tr>-->
																									</table>

																								</div>
																							</h:panelGroup>
																						</a4j:outputPanel></td>
																				</tr>
																			</table> <a4j:outputPanel id="pdfrActsRules">
																				<h:panelGroup
																					rendered="#{customHomeAction.pdfRenderActsRules}">


																					<iframe width="100%" height="700px"
																						src="#{customHomeAction.srcMethod}"></iframe>


																				</h:panelGroup>
																			</a4j:outputPanel>

																		</td>
																	</tr>
																	<tr></tr>
																	<tr></tr>
																	<tr>
																		<td align="center" width="100%" colspan="3"><a4j:outputPanel
																				id="pdfread">
																				<h:panelGroup
																					rendered="#{customHomeAction.pdfRender}">


																					<iframe width="100%" height="700px"
																						src="#{customHomeAction.srcMethod}"></iframe>


																				</h:panelGroup>
																			</a4j:outputPanel></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td align="center"><a4j:outputPanel id="back">
																				<h:commandButton value="Back"
																					image="/images/back.png"
																					action="#{customHomeAction.backMethod}"
																					style="border:3; background: transparent; font-size: 15px ;"
																					rendered="#{customHomeAction.backRendered}"></h:commandButton>

																			</a4j:outputPanel></td>
																	</tr>
																	<tr>
																		<td colspan="3" align="center" valign="top">
																			<div style="border-width: 1px;">
																				<table cellspacing="0" cellpadding="0" width="100%">
																					<tbody>
																						<tr>
																							<td height="5"></td>
																						</tr>

																						<tr>

																							<td height="28"
																								style="text-align: center; background-image: url('/doc/LDA/welcome/homepage/top/grad_img.png'); background-size: 100% 28px; background-repeat: no-repeat; font-size: 12px; color: Black; padding-top: 0px;"
																								colspan="8"><a4j:outputPanel id="render1">

																									<h:outputText
																										rendered="#{customHomeAction.rendere1}"
																										value="Public Utility Actions -"
																										styleClass="generalHeaderStyle"
																										style="FONT-SIZE: medium; align:left; COLOR: #F3F1ED; FONT-WEIGHT: bold;">
																									</h:outputText>
																									<blink><h:outputText
																										rendered="#{customHomeAction.rendere1}"
																										value=" For Any Help "
																										
																										style="COLOR: #930000; FONT-SIZE: large;text-decoration:blink;font-weight: bold;">
																									</h:outputText></blink><a target="_blank"
																												href="/doc/LDA/welcome/helpdesk.pdf" style=""> <h:outputText
																										rendered="#{customHomeAction.rendere1}"
																										value=" Click Here"
																										styleClass="generalHeaderStyle"
																										></h:outputText></a>
																								</a4j:outputPanel></td>
																						</tr>


																						<tr>
																							<td colspan="8" align="center"><a4j:outputPanel
																									id="render4">
																									<h:panelGroup
																										rendered="#{customHomeAction.rendere1}">
																										<hr width="95%" size="2"></hr>
																										<br />
																									</h:panelGroup>
																								</a4j:outputPanel></td>
																						</tr>
																						<tr>
																							<td align="center"><a4j:outputPanel
																									id="render2">
																									<table>
																										<tr style="#{customHomeAction.style}">
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Scheme+And+Sectors">
																													<h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/snds.png"></h:graphicImage>
																											</a> <br /></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/New+Scheme"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/res.png">

																													</h:graphicImage>
																											</a></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Commercial"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/NEW_COMM.png"></h:graphicImage>
																											</a> <br /></td>


																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/APP_STATUS"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/appstatus.png"></h:graphicImage>
																											</a> <br /></td>

																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/REG_MOB_NO"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/MOBREG.png"></h:graphicImage>
																											</a></td>

																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Lottery+Information">
																													<h:graphicImage height="120"
																														rendered="#{customHomeAction.rendere1}"
																														width="100"
																														value="/icons/PublicLottery.png"></h:graphicImage>
																											</a></td>


																											<td style="#{customHomeAction.style}">


																												<a target="_self"
																												href="/portal/Home/Home/REG_DET"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/PROPERTY_INQUIRY.jpg"></h:graphicImage>
																											</a>

																											</td>

																											<td style="#{customHomeAction.style}">


																												<a target="_self"
																												href="/portal/Home/Home/Map_Entry_Form"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/ONLINEMAPSTATUS.png"></h:graphicImage>
																											</a>

																											</td>
																											<td style="#{customHomeAction.style}">


																												<a target="_self"
																												href="/portal/Home/Home/MAP_STATUS"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/mapstatus.png"></h:graphicImage>
																											</a>

																											</td>

																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/PROPERTY_INQUIRY">
																													<b> <h:graphicImage height="120"
																															rendered="#{customHomeAction.rendere1}"
																															width="100" value="/icons/ECHALLAN.png"></h:graphicImage>
																												</b>
																											</a></td>






																										</tr>


																										<tr style="#{customHomeAction.style}">


																											<td style="#{customHomeAction.style}"><a4j:commandLink
																													actionListener="#{customHomeAction.listenerFrActsRules}"
																													reRender="renderActRule,back3,imgread,pdfread,back,render1,render2,render3,render4,render5,render6,render7,render8,render9">
																													<b> <h:graphicImage height="120"
																															rendered="#{customHomeAction.rendere1}"
																															width="100" value="/icons/ACTSRULES.png"></h:graphicImage>
																													</b>
																												</a4j:commandLink></td>
																											<td style="#{customHomeAction.style}"><a4j:commandLink
																													id="uac" onclick="true"
																													actionListener="#{customHomeAction.listener3 }"
																													reRender="back3,imgread,pdfread,back1,back,render1,render10,render2,render3,render4,render5,render6,render7,render8,render9">
																													<b>
																														<!--   <h:graphicImage height="120" rendered="#{customHomeAction.rendere1}"
																					width="100" value="/icons/Publicunauthorised.png"></h:graphicImage>-->

																														<a target="_blank"
																														href="/doc/LDA/uploads/#{customHomeAction.maxpdf}.pdf">
																															<h:graphicImage
																																rendered="#{customHomeAction.rendere1}"
																																height="120" width="100"
																																value="/icons/Publicunauthorised.png">
																															</h:graphicImage>
																													</a>

																													</b>
																												</a4j:commandLink></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Notice+Display">
																													<h:graphicImage height="120"
																														rendered="#{customHomeAction.rendere1}"
																														width="100" value="/icons/notices.png"></h:graphicImage>
																											</a></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Go+Search"> <h:graphicImage
																														height="120"
																														rendered="#{customHomeAction.rendere1}"
																														width="100" value="/icons/orders.png"></h:graphicImage>
																											</a></td>

																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Officer+Contact">
																													<h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/SERVICE_DIR.png">
																													</h:graphicImage>
																											</a></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/WRK_DIS"> <b>
																														<h:graphicImage height="120"
																															rendered="#{customHomeAction.rendere1}"
																															width="100" value="/icons/wrkdistri.png"></h:graphicImage>
																												</b></a></td>


																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Officer+Salary">
																													<h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/salary.png"></h:graphicImage>
																											</a></td>


																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Downloads"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/download.png"></h:graphicImage>
																											</a> <br /></td>
																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/Tender+List"> <h:graphicImage
																														rendered="#{customHomeAction.rendere1}"
																														height="120" width="100"
																														value="/icons/PublicTenders.png"></h:graphicImage>
																											</a></td>





																											<td style="#{customHomeAction.style}"><a
																												target="_self"
																												href="/portal/Home/Home/PRADHIKARAN"> <b>
																														<h:graphicImage height="120"
																															rendered="#{customHomeAction.rendere1}"
																															width="100"
																															value="/icons/pradhikaran.png"></h:graphicImage>
																												</b></a></td>





																										</tr>




																									</table>
																								</a4j:outputPanel></td>
																						</tr>

																					</tbody>
																				</table>

																			</div>
																		</td>
																		<td></td>

																	</tr>
																	<tr>
																		<td></td>
																		<td></td>

																	</tr>
																	<tr>
																		<td></td>
																		<td></td>

																	</tr>
																</table>
														<tr>
															<td><a4j:outputPanel id="render7">
																	<h:panelGroup rendered="#{customHomeAction.rendere1}">
																		<table width="100%">
																			<tr>
																				<td align="center">
																					<!--	<div
															style="width: 100%; max-height: 200px; overflow: auto;">
															<p align="center">
																<h:outputText
																	value="#{applicationEntryAction.noRecordsText}"
																	styleClass="generalHeaderStyle"
																	style="FONT-SIZE: large; align:left; COLOR: #2D2D2D; FONT-WEIGHT: bold;">
																</h:outputText>
															</p>

															<rich:dataTable width="100%" id="addtable1"
																value="#{applicationEntryAction.displayNotificationProposalList }"
																var="list" headerClass="TableHead"
																footerClass="TableHead" rows="10"
																rendered="#{applicationEntryAction.renderTable}"
																rowClasses="TableRow1,TableRow2">
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.allotementMode}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText
																		value="#{list.registrationSchemeTypeDisplay}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>

																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.SchemeName}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.schemeName}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>

																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.SubSchemeName}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.subSchemeName}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.SectorName}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.sectorName}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.PropertyType}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.propertyTypeName}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.PropertySubType}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.propertySubTypeName}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>
																	<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.AcrhiTecturalPlan}"
																			styleClass="generalHeaderStyle"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.archPlanName}"
																		styleClass="generalHeaderStyle"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.FloorDesc}"
																			styleClass="generalHeaderStyle"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.floorName}"
																		styleClass="generalHeaderStyle"></h:outputText>
																</h:column>   																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.Quantity}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.quantity}"
																		styleClass="generalExciseStyle3" ></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.TotalPrice}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.totalPrice}"
																		styleClass="generalExciseStyle3"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.EligibilityDesc}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputText value="#{list.eligibilityDesc}"
																		styleClass="generalHeaderStyle3"></h:outputText>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.bookletCost}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>

																	<h:outputText value="#{list.bookletCost}"
																		styleClass="generalExciseStyle3"></h:outputText>

																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.download}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputLink
																		value="/doc/LDA/Property/Notification/Booklet/#{list.bookletName}"
																		target="_blank">
																		<h:outputText styleClass="generalExciseStyle3"
																			id="text171" value="#{msgBundle.viewBooklet}"
																			style="color: blue; font-family: serif; font-size: 12pt">
																		</h:outputText>
																	</h:outputLink>
																</h:column>
																<h:column>
																	<f:facet name="header">
																		<h:outputText value="#{msgBundle.download}"
																			styleClass="generalHeaderStyle" style="FONT-WEIGHT: bold;"></h:outputText>
																	</f:facet>
																	<h:outputLink
																		value="/doc/LDA/Property/Notification/Affidavit/#{list.affidavitName}"
																		target="_blank">
																		<h:outputText styleClass="generalExciseStyle3"
																			id="text17" value="#{msgBundle.viewAffidavit}"
																			style="color: blue; font-family: serif; font-size: 12pt">
																		</h:outputText>
																	</h:outputLink>
																</h:column>
																<f:facet name="footer">
																	<rich:datascroller for="addtable1" />
																</f:facet>
															</rich:dataTable>
															<p>
																<h:outputText styleClass="generalHeaderStyle"
																	style="FONT-WEIGHT: bold; FONT-STYLE: italic; FONT-SIZE: large; align:left; line-height: 50px; COLOR:gray;"
																	rendered="#{!applicationEntryAction.renderTable}"
																	value="#{applicationEntryAction.noRecordsText2}"></h:outputText>

															</p>
														</div>-->
																				</td>
																			</tr>


																		</table>
																	</h:panelGroup>
																</a4j:outputPanel></td>
															<td></td>
														</tr>
														</td>
														</tr>
													</table>
												</h:panelGroup>
											</a4j:outputPanel></td>
									</tr>
								</table>
							</h:panelGroup></td>
					</tr>
					<tr>
						<td align="center" width="100%"><a4j:outputPanel
								id="render5">


								<h:panelGroup rendered="#{customHomeAction.pdfRender1}">

									<h:commandButton value="Back" image="/images/back.png"
										action="#{customHomeAction.backMethod1}"
										style="border:3; background: transparent; font-size: 15px "
										rendered="#{customHomeAction.backRendered1}"></h:commandButton>

									<iframe width="100%" height="700px"
										src="#{customHomeAction.srcMethod}"></iframe>


								</h:panelGroup>
							</a4j:outputPanel></td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td align="center"><a4j:outputPanel id="back1">

								<h:commandButton value="Back" image="/images/back.png"
									action="#{customHomeAction.backMethod1}"
									style="border:3; background: transparent; font-size: 15px "
									rendered="#{customHomeAction.backRendered1}"></h:commandButton>


							</a4j:outputPanel></td>
					</tr>

				</table>

			</h:form>
		</center>
	</f:view>
</ui:composition>