class SumOfMultiples

  def initialize(*factors)
    @factors = factors
  end
  
  def to(limit)
    SumOfMultiples.to(limit, @factors)
  end

  def self.to(limit, factors = [3, 5])
    multiples = (1...limit).select do |num|
      factors.any? { |factor| num % factor == 0 }
    end

    multiples.reduce(:+) || 0
  end
end
