class SumOfMultiples

  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(max)
    numbers.map {|n| self.class.sum_of_multiples(n, max)}.reduce(:+) - 
      self.class.sum_of_multiples(numbers.reduce(:*), max)
  end

  def self.to(max)
    new(3, 5).to(max)
  end

  private

  def self.sum_of_multiples(number, max)
     number >= max ? 0 :
       (1..((max - 1) / number)).map {|n| n * number}.reduce(:+)
  end

end
