class SumOfMultiples
  attr_accessor :numbers

  def initialize *numbers
    @numbers = numbers.empty? ? [3, 5] : numbers
  end

  def to n
    arg = []
    0.upto(n-1) do |i|
      for number in numbers
        arg << i if i % number == 0 unless arg.include?(i)
      end
    end
    arg.reduce(:+)
  end

  class << self
    def to n
      new.to(n)
    end
  end
end

#puts SumOfMultiples.to(4)

#puts SumOfMultiples.new(43, 47).to(10000)
