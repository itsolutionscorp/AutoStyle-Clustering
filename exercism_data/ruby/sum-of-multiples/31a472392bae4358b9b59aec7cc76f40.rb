class SumOfMultiples
  def initialize(*divisors)
    @divisors = divisors
  end

  def to(limit)
    self.class.to(limit, @divisors)
  end

  def self.to(limit, divisors = [3, 5])
    (0...limit).inject(0) do |sum, num|
      match(num, divisors) ? sum + num : sum
    end
  end

  def self.match(num, divisors)
    divisors.any? { |divisor| num % divisor == 0 }
  end
end
