class PrimeFactors
  def self.for(n)
    return [] if n <= 1

    divisor = 2
    while n % divisor != 0
      divisor += 1
    end

    [divisor] + self.for(n / divisor)
  end
end
