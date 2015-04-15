class Fixnum
  def factor_of?(other)
    other % self == 0
  end

  def divisible_by?(factors)
    factors.any? { |t| t.factor_of?(self) }
  end
end

class SumOfMultiples
  def initialize(*numbers)
    @numbers = numbers
  end

  def to(max)
    SumOfMultiples.to(max, @numbers)
  end

  def self.to(max, factors = [3, 5])
    (0..max-1).to_a.select do |n|
      n.divisible_by?(factors)
    end
    .inject(&:+)
  end
end
