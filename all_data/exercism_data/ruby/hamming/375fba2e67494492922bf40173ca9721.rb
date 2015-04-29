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

  def hamming_distance_to(homologous_strand)
    mutations = 0
    @strand.each_char.each_with_index { |nucleotide, index|
      next if homologous_strand[index].nil?
      mutations += 1 if nucleotide != homologous_strand[index]
    }

    mutations
  end
end
