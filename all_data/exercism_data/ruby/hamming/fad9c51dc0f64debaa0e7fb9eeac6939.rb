require 'matrix'

class Hamming
  class << self
    def compute(x, y)
      strand_a = x.each_char.to_a
      strand_b = y.each_char.to_a
      acc = []
      strand_a.each_with_index do |strand, index|
        acc << Strand.compare(strand, strand_b[index])
      end

      acc.inject(:+)
    end
  end
end

class Strand
  attr_accessor :strand
  class << self
    def compare(a, b)
      new(a).distance(new(b))
    end
  end

  def initialize(strand)
    @strand = strand
  end

  def distance(other_strand)
    self == other_strand ? 0 : 1
  end

  def ==(other_strand)
    return true if @strand == other_strand.strand
  end
end
