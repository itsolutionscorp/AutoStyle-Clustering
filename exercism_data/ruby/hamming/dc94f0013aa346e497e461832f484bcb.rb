class Hamming
  attr_reader :comparisons

  def self.compute(x, y)
    hammer = new(x.to_a, y.to_a)
    hammer.computation
  end

  def initialize(xs, ys)
    @comparisons = xs.zip(ys)
  end

  def differences
    comparisons.select { |a| a.first != a.last unless a.include?(nil) }
  end

  def computation
    differences.size
  end
end

class String
  def to_a
    split("")
  end
end

# Disgusting one liner example:
#
# class Hamming
#   def self.compute(x, y)
#     x.split("").zip(y.to_a).select { |a| a.first != a.last unless a.include?(nil) }.size
#   end
# end
