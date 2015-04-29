class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    HammingDistance.new(self, DNA.new(other)).distance
  end

  def nucleotides
    strand.chars
  end

  def length
    strand.length
  end

  attr_reader :strand

  class HammingDistance

    def initialize(dna, other)
      @dna   = dna
      @other = other
    end

    def distance
      pairs.count { |(a, b)| a != b }
    end

    private

    def pairs
      dna.nucleotides.zip(other.nucleotides).take(length)
    end

    def length
      [dna.length, other.length].min
    end

    attr_reader :dna, :other
  end

end
