class Hamming

  attr_reader :nucleotides, :other_nucleotides

  def self.compute(*args)
    new(*args).compute
  end

  def initialize(strand, other_strand)
    n = comparable_nucleotides(strand, other_strand)

    @nucleotides = strand.chars.take(n)
    @other_nucleotides = other_strand.chars.take(n)
  end

  def compute
    nucleotides.each_with_index.inject(0) do |sum, (n, index)|
      sum += (n != other_nucleotides[index] ? 1 : 0)
    end
  end

  private

  def comparable_nucleotides(strand, other_strand)
    [strand.length, other_strand.length].min
  end

end
