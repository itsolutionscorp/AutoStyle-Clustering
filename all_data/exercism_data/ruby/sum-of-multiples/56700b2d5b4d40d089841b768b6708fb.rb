class SumOfMultiples

  attr_reader :divisors

  def initialize(*divisors)
    @divisors = divisors
  end

  def self.to(limit)
    SumOfMultiples.new(3,5).to(limit)
  end

  def to(limit)
    (0...limit).select do |n|
      divisors.any? { |divisor| n % divisor == 0 }
    end.reduce(:+)
  end

end
