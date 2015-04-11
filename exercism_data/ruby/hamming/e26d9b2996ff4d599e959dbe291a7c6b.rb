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
    distance = 0
    common_length.times do |nucleotide|
      distance +=1 if @strand1[nucleotide] != @strand2[nucleotide]
    end
    distance
  end

  private

  def common_length
    # only positions that exist in both strands are considered
    @strand2.length > @strand1.length ? @strand1.length : @strand2.length
  end

end
