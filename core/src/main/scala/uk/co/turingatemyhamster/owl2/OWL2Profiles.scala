package uk.co.turingatemyhamster
package owl2

/**
 *
 *
 * @author Matthew Pocock
 */
object OWL2Profiles {

  trait Full
    extends IriModule

    with EntitiesLiteralsAnonymousIndividualsModule
    with DataRangesModule
    with DataPropertyExpressionModule
    with DataPropertyCardinalityRestrictionsModule
    with DataPropertyRestrictionsModule
    with ObjectPropertyExpressionsModule
    with ObjectPropertyCardinalityRestrictionsModule
    with ObjectPropertyRestrictionsModule
    with PropositionalConnectivesAndEnumerationOfIndividualsModule

    with OntologyModule
    with AnnotationsModule

    with DeclarationModule

}
