class SumOfMultiples
  def self.to(number, factors=[3,5])
    new(*factors).to(number)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(number)
    (0...number).select do |num|
      multiple_of_factors?(num)
    end.inject(:+)
  end

  private

  attr_reader :factors

  def multiple_of_factors?(number)
    factors.any? do |factor|
      number % factor == 0
    end
  end
end
