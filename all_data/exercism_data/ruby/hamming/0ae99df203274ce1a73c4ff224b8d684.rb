require 'forwardable'

class Hamming
  def self.compute(dna1, dna2)
    @strand1 = Dna.new(dna1)
    @strand2 = Dna.new(dna2)
    @strand1.hamming_distance_to(@strand2)
  end
end

class Dna
  extend Forwardable

  def_delegators :@strand, :[]

  def initialize(nucleotides = String.new)
    @strand = nucleotides
  end

  def hamming_distance_to(other_strand)
    @strand.chars.each_with_index.inject(0) do |mutations, (nucleotide, index)|
      if other_strand[index] && nucleotide != other_strand[index]
        mutations + 1
      else
        mutations
      end
    end
  end
end
