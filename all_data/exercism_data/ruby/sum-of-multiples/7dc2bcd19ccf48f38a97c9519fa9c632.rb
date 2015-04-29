module SumOfMultiples
  def self.new(*divisors)
    CustomDivisorSummation.new divisors
  end

  def self.to(max, divisors = [3, 5])
    (0...max).select{|n| divisors.any?{|d| n % d == 0 } }.reduce :+
  end

  private

  class CustomDivisorSummation
    def initialize divisors
      @divisors = divisors
    end

    def to max
      SumOfMultiples.to max, @divisors
    end
  end
end
