package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DeclarationModuleAst extends DeclarationModule {

  importedModules : OntologyModule  with
    EntitiesLiteralsAnonymousIndividualsModule with
    IriModule {
    type Axiom = ast.Axiom
  } =>

  override final type Declaration = ast.Declaration

}

case class Declaration(axiomAnnotations: List[Annotation], entity: Entity) extends Axiom