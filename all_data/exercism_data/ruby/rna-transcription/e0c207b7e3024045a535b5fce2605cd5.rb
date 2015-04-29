require 'set'

class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  def self.of_dna(sequence)
    raise_sequence_exception 'DNA', sequence unless dna? sequence
    sequence.tr DNA_NUCLEOTIDES, RNA_NUCLEOTIDES
  end

  def self.of_rna(sequence)
    raise_sequence_exception 'RNA', sequence unless rna? sequence
    sequence.tr RNA_NUCLEOTIDES, DNA_NUCLEOTIDES
  end

  def self.dna?(sequence)
    subset? DNA_NUCLEOTIDES, sequence.upcase
  end

  def self.raise_sequence_exception(label, sequence)
    raise ArgumentError.new "\"#{sequence}\" is not a valid #{label} sequence"
  end
  private_class_method :raise_sequence_exception

  def self.rna?(sequence)
    subset? RNA_NUCLEOTIDES, sequence.upcase
	end

  def self.subset?(nucleotides, sequence)
    Set.new(sequence.chars).subset? Set.new(nucleotides.chars)
  end
  private_class_method :subset?
end
