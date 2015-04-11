class SumOfMultiples
  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(input)
    self.class.to(input, numbers)
  end

  def self.to(input, factors=[3,5])
    range = (0...input)

    range.select do |number|
      number if factors.any? { |multiple| number % multiple == 0 }
    end.inject(:+)

  end

end
