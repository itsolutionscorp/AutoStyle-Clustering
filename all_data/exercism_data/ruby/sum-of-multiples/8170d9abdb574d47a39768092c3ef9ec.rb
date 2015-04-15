class SumOfMultiples
  def self.to(n)
    new(3, 5).to(n)
  end

  def initialize(*factors)
    @factors = factors
  end

  attr_reader :factors

  def to(n)
    divisible_by_factors(n).inject(0, :+)
  end

  private

  def divisible_by_factors(n)
    (1...n).lazy.select do |x|
      factors.any? {|y| x % y == 0 }
    end
  end
end
