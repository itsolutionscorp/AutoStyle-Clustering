class Hamming

  def self.compute(strand1, strand2)
    Hamming::Nucleotide.new(strand1).hamming_distance(Hamming::Nucleotide.new(strand2))
  end

  class Nucleotide
    attr_reader :strand

    def initialize(strand)
      @strand = strand
    end

    def hamming_distance(nucleotide)
      len = [strand.size, nucleotide.strand.size].min
      (0...len).count {|i| strand[i] != nucleotide.strand[i]}
    end
  end
end
