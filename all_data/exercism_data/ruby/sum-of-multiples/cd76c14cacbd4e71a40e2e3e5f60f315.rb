class SumOfMultiples
  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(input)
    range = (0...input)

    range.select do |number|
      number if numbers.any? { |multiple| number % multiple == 0 }
    end.inject(:+)

  end

  def self.to(input, factors=[3,5])
    range = (0...input)

    range.select do |number|
      number if factors.any? { |multiple| number % multiple == 0 }
    end.inject(:+)

  end

end
