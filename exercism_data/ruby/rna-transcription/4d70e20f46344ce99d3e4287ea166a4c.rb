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
  include NucleicAcids
  extend  NucleicAcids
	
  module_function

  def of_dna(sequence)
    sequence = sequence.upcase
    sequence_error 'DNA', sequence unless dna? sequence
    sequence.tr DNA_NUCLEOTIDES, RNA_NUCLEOTIDES
  end

  def of_rna(sequence)
    sequence = sequence.upcase
    sequence_error 'RNA', sequence unless rna? sequence
    sequence.tr RNA_NUCLEOTIDES, DNA_NUCLEOTIDES
  end

  def sequence_error(label, sequence)
    fail ArgumentError.new "#{sequence} is not a valid #{label} sequence"
  end
  private_class_method :sequence_error
end
