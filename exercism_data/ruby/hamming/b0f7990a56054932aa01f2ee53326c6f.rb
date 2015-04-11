class Hamming
  def self.compute(a, b)
    Strand.new(a).difference_between(Strand.new(b))
  end
end

class Strand
  attr_accessor :points

  def initialize(str)
    self.points = str.chars.map { |char| StrandPoint.new(char) }
  end

  def difference_between(other_strand)
    points.zip(other_strand.points).count { |a, b| a && b && a.differs_from?(b) }
  end
end

class StrandPoint
  attr_accessor :char

  def initialize(char)
    self.char = char
  end

  def differs_from?(other_point)
    char != other_point.char
  end
end
