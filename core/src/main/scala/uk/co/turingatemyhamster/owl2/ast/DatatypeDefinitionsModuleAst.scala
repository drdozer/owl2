package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DatatypeDefinitionsModuleAst extends owl2.DatatypeDefinitionsModule {

  importedModules : owl2.AxiomsModule =>

  override final type DatatypeDefinition = ast.DatatypeDefinition

}

case class DatatypeDefinition(axiomAnnotations: List[Annotation] = Nil,
                              dataRange: DataRange,
                              datatype: Datatype) extends Axiom