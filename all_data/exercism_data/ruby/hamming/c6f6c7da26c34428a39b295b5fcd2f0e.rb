class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = Strand.new(strand)
  end

  def hamming_distance(dna_strand)
    foreign_strand = Strand.new(dna_strand)
    HammingDistance.count(strand, foreign_strand)
  end
end

module HammingDistance
  def self.count(strand, other_strand)
    strand.nucleotides.zip(other_strand.nucleotides).count do |original, foreign|
      foreign && original != foreign
    end
  end
end

Strand = Struct.new(:sequence) do
  def nucleotides
    sequence.chars
  end
end
