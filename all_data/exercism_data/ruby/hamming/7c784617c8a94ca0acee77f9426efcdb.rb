module Hamming
  def self.compute(one_strand, other_strand)
    call(DnaStrand.from_string(one_strand), DnaStrand.from_string(other_strand))
  end

  def self.call(one, other)
    one.nucleotides.zip(other.nucleotides).count do |one_nucleotide, other_nucleotide|
      one_nucleotide != other_nucleotide
    end
  end
end

class DnaStrand < Struct.new(:nucleotides)
  def self.from_string(dna_string)
    DnaStrand.new(dna_string.chars.map { |type_char| Nucleotide.new(type_char) })
  end

  Nucleotide = Struct.new(:type)
end
