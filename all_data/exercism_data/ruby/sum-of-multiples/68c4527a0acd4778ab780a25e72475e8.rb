class SumOfMultiples
  DEFAULT_FACTORS = [3, 5]

  def initialize(*factors)
    @factors = *factors || DEFAULT_FACTORS
  end

  def to(n)
    metaclass = class << self; self end
    metaclass.to(n, @factors)
  end

  def self.to(n, factors = DEFAULT_FACTORS)
    (factors[0]..n - 1).select { |i|
      factors.any? { |factor| (i % factor).zero? }
    }.reduce(:+) || 0
  end
end
