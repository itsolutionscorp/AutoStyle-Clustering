# sum.rb
class SumOfMultiples
  attr_reader :factors

  def self.to(limit, factors = [3, 5])
    factors.reduce([0]) do |multiples, factor|
      multiples + (factor..(limit - 1)).step(factor).to_a
    end.uniq.reduce(:+)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(limit)
    SumOfMultiples.to(limit, factors)
  end
end
