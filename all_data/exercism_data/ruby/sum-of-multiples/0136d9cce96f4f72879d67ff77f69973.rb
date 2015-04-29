class SumOfMultiples
  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers.empty? ?  [3, 5] : numbers
  end

  def to(number)
    max = number - 1

    multiples = (0..max).select do |i|
      numbers.find { |multiple| i % multiple == 0 }
    end

    multiples.inject(&:+)
  end

  class << self
    def to(number)
      new.to(number)
    end
  end
end
