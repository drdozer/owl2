package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait AxiomsModuleAst extends owl2.AxiomsModule {

  importedModules: owl2.DeclarationModule with owl2.OntologyModule {
    type Declaration = ast.Declaration
  } =>


}

trait ClassAxiom extends Axiom

trait ObjectPropertyAxiom extends Axiom

trait DataPropertyAxiom extends Axiom

trait DatatypeDefinition extends Axiom

trait hasKey extends Axiom

trait Assertion extends Axiom

trait AnnotationAxiom extends Axiom