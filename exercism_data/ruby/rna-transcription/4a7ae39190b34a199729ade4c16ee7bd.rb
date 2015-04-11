# complement.rb
module Complement
  module_function

  def of_rna(rna)
    NucleotideSequence.new(rna).to_dna
  end

  def of_dna(dna)
    NucleotideSequence.new(dna).to_rna
  end
end

# nucleotide_sequence.rb
class NucleotideSequence
  DNA_TO_RNA_COMPLEMENTS = {
    'adenine' => 'uracil',
    'cytosine' => 'guanine',
    'guanine' => 'cytosine',
    'thymidine' => 'adenine'
  }
  RNA_TO_DNA_COMPLEMENTS = {
    'adenine' => 'thymidine',
    'cytosine' => 'guanine',
    'guanine' => 'cytosine',
    'uracil' => 'adenine'
  }
  INITIAL_BY_NUCLEOTIDE = {
    'adenine' => 'A',
    'cytosine' => 'C',
    'guanine' => 'G',
    'thymidine' => 'T',
    'uracil' => 'U'
  }
  NUCLEOTIDE_BY_INITIAL = {
    'A' => 'adenine',
    'C' => 'cytosine',
    'G' => 'guanine',
    'T' => 'thymidine',
    'U' => 'uracil'
  }

  def initialize(sequence)
    @sequence = sequence.chars.map do |nucleotide|
      NUCLEOTIDE_BY_INITIAL[nucleotide]
    end
  end

  def to_dna
    sequence = @sequence.map do |nucleotide|
      nucleotide = RNA_TO_DNA_COMPLEMENTS[nucleotide]
      INITIAL_BY_NUCLEOTIDE[nucleotide]
    end
    sequence.join
  end

  def to_rna
    sequence = @sequence.map do |nucleotide|
      nucleotide = DNA_TO_RNA_COMPLEMENTS[nucleotide]
      INITIAL_BY_NUCLEOTIDE[nucleotide]
    end
    sequence.join
  end
end
