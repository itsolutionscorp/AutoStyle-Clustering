module Nucleotide
  DNA_NUCLEOTIDES = {
    'A' => 'Adenosine',
    'C' => 'Cytidine',
    'G' => 'Guanosine',
    'T' => 'Thymidine'
  }
  RNA_NUCLEOTIDES = {
    'A' => 'Adenosine',
    'C' => 'Cytidine',
    'G' => 'Guanosine',
    'U' => 'Uracil'
  }
  NUCLEOTIDES = DNA_NUCLEOTIDES.keys | RNA_NUCLEOTIDES.keys
end

class RNA
  include Nucleotide
end

class DNA
  include Nucleotide
  def initialize(dna)
    @dna = dna
  end

  def count(nucleotide)
    raise ArgumentError unless NUCLEOTIDES.include?(nucleotide)
    @dna.chars.count(nucleotide)
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.keys.each_with_object(Hash.new(0)) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end
end
