class SumOfMultiples
  attr_reader :numbers

  def initialize(*numbers)
    @numbers = numbers
  end

  def to(max)
    SumOfMultiples.to(max, numbers)
  end

  def self.to(max, multiples = [3, 5])
    (0...max).to_a.select do |n|
      multiples.any?{|m| n % m == 0}
    end.inject(&:+)
  end

end
