module  Hamming
  def self.compute(*strands)
    s_min, s_max = strands.map! { |n| Strand.new(n) }.sort
    s_min.distance_to(s_max)
  end
end
class Strand
  attr_reader :nucleotids

  def initialize(obj)
    @nucleotids = obj.chars
  end

  def <=>(other)
    length <=> other.length
  end

  def length
    @nucleotids.length
  end

  def distance_to(other_strand)
    @nucleotids.zip(other_strand.nucleotids).count { |a, b| a != b }
  end
end
