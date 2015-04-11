class SumOfMultiples
  def self.to(number, factors=[3,5])
    new(*factors).to(number)
  end

  def initialize(*factors)
    @factors = factors
  end

  def to(number)
    (0...number).select do |number|
      factor?(number)
    end.inject(:+)
  end

  private

  attr_reader :factors

  def factor?(number)
    factors.any? do |multiple|
      number % multiple == 0
    end
  end
end
