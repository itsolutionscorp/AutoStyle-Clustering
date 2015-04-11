class DNA
  attr_reader :nucleotides

  def initialize strand
    @nucleotides = strand.chars.map{ |char| Nucleotide.new(char) }
  end

  def hamming_distance other
    other_strand = DNA.new(other)
    nucleotides.size - nucleotides.count{ |n| n == other_strand.nucleotides.shift }
  end

  class Nucleotide
    def initialize(char)
      @char = char
    end

    def to_s
      @char.to_s
    end

    def ==(other)
      (@char.nil? ^ other.nil?) || (to_s == other.to_s)
    end
  end
end
