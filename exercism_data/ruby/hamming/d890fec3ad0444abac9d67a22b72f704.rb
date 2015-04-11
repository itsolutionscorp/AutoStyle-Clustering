class DNA
  attr_reader :nucleotides

  def initialize strand
    @nucleotides = strand.each_char.map{ |char| Nucleotide.new(char) }
  end

  def hamming_distance other
    other_strand = DNA.new(other)
    nucleotides.each.reduce(0) do |distance, nucleotide|
      distance + (nucleotide == other_strand.nucleotides.shift ? 0 : 1)
    end
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
