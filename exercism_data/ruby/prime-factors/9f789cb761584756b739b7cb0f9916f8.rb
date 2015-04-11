class PrimeFactors

  def self.for(num)
    prime_factors = []
    divisor = 2

    while num > 1
      while (num % divisor) == 0
        prime_factors << divisor
        num /= divisor
      end
      divisor += 1
    end
    prime_factors
  end

end
