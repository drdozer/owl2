package uk.co.turingatemyhamster
package owl2

/**
 *
 *
 * @author Matthew Pocock
 */
object OWL2Profiles {

  trait Full
    extends AnnotationsModule
    with PropositionalConnectivesAndEnumerationOfIndividualsModule
    with OntologyModule
    with ObjectPropertyRestrictionsModule
    with ObjectPropertyCardinalityRestrictionsModule
    with ObjectPropertyExpressionsModule
    with DataPropertyRestrictionsModule
    with DataPropertyCardinalityRestrictionsModule
    with DataPropertyExpressionModule
    with DataRangesModule
    with EntitiesLiteralsAnonymousIndividualsModule
    with IriModule

}
