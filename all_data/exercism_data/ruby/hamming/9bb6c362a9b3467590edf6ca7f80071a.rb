class Hamming

  def self.compute(strand1, strand2)

    DNAPair.new(strand1, strand2).distance

  end

end

class DNAPair

  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def distance
    common_length.times.count { |nucleotide| @strand1[nucleotide] != @strand2[nucleotide] }
  end

  private

  def common_length
    # only positions that exist in both strands are considered
    [@strand1.size, @strand2.size].min
  end

end
