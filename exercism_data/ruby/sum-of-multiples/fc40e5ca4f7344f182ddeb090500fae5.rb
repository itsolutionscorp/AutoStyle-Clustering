class SumOfMultiples
  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(input)
    range = (0...input)
    sum = []
    range.select do |num|
      numbers.select do |multiple|
        sum << num if num % multiple == 0
      end
    end

    sum.uniq.inject(:+)
  end

  def self.to(input)
    sum = []
    range = (0...input)

    range.each do |num|
      if (num % 3 == 0)|| (num % 5 == 0)
        sum << num
      end
    end

    sum.uniq.inject(:+)
  end

end
