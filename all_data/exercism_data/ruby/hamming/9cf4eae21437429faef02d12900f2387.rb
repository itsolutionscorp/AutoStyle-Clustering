module Hamming
  def self.compute(one_strand, other_strand)
    DnaStrand.from_string(one_strand).distance_from(DnaStrand.from_string(other_strand))
  end
end

class DnaStrand < Struct.new(:nucleotides)
  def self.from_string(dna_string)
    DnaStrand.new(dna_string.chars.map { |type_char| Nucleotide.new(type_char) })
  end

  Nucleotide = Struct.new(:type)

  def distance_from(other, distance_function = HammingDistance)
    distance_function.call(self, other)
  end
end

HammingDistance = ->(one, other) {
  one.nucleotides.zip(other.nucleotides).count do |one_nucleotide, other_nucleotide|
    one_nucleotide != other_nucleotide
  end
}
