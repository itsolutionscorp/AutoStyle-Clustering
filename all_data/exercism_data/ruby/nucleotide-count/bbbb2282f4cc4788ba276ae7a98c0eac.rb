module Nucleotide
  NUCLEOTIDES = {
    'A' => 'Adenosine',
    'C' => 'Cytidine',
    'G' => 'Guanosine',
    'T' => 'Thymidine',
    'U' => 'Uracil'
  }
  DNA_NUCLEOTIDES = NUCLEOTIDES.select { |k, v| k != 'U' }
  RNA_NUCLEOTIDES = NUCLEOTIDES.select { |k, v| k != 'T' }
end

class RNA
  include Nucleotide
end

class DNA
  include Nucleotide
  def initialize(sequence)
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @sequence.chars.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.keys.each_with_object(Hash.new(0)) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end
end
