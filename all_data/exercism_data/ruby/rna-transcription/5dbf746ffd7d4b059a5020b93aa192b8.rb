# Credit to workmad3@freenode for great advice on a cleaner, looser
# module dependency structure.

require 'set'
  
module NucleicAcids
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  module_function

  def dna?(sequence)
    subset? DNA_NUCLEOTIDES, sequence.upcase
  end

  def rna?(sequence)
    subset? RNA_NUCLEOTIDES, sequence.upcase
  end

  def subset?(nucleotides, sequence)
    Set.new(sequence.chars).subset? Set.new(nucleotides.chars)
  end
  private_class_method :subset?
end

module Complement
  module_function

  def of_dna(sequence)
    sequence_error 'DNA', sequence unless NucleicAcids::dna? sequence
    sequence.upcase.tr NucleicAcids::DNA_NUCLEOTIDES, NucleicAcids::RNA_NUCLEOTIDES
  end

  def of_rna(sequence)
    sequence_error 'RNA', sequence unless NucleicAcids::rna? sequence
    sequence.upcase.tr NucleicAcids::RNA_NUCLEOTIDES, NucleicAcids::DNA_NUCLEOTIDES
  end

  def sequence_error(label, sequence)
    fail ArgumentError.new "#{sequence} is not a valid #{label} sequence"
  end
  private_class_method :sequence_error
end
