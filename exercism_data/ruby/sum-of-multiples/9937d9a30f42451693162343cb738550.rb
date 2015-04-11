class SumOfMultiples

  def initialize(*divisors)
    @divisors = divisors
  end
  
  def self.to(limit)
    sum = 0

    for i in (1...limit)
      sum += i if i % 3 == 0 || i % 5 == 0
    end

    sum
  end

  def to(limit)
    sum = 0

    for i in (1...limit)
      if @divisors.any? { |divisor| i % divisor == 0 }
        sum += i
      end
    end

    sum
  end
end
