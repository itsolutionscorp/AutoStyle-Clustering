class SumOfMultiples
  attr_reader :factors

  def initialize(*factors)
    @factors = factors 
  end

  def to(input)
    range = (0...input)

    range.select do |number|
      number if factors.any? { |multiple| number % multiple == 0 }
    end.inject(:+)

  end

  def self.to(input, factors=[3,5])
    new(*factors).to(input)
  end

end
