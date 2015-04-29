class SumOfMultiples

  def initialize(*divisors)
    @divisors = divisors
  end
  
  def to(limit)
    SumOfMultiples.to(limit, @divisors)
  end

  def self.to(limit, divisors = [3, 5])
    sum = 0

    for i in (1...limit)
      if divisors.any? { |divisor| i % divisor == 0 }
        sum += i
      end
    end

    sum
  end
end
