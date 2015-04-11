class PrimeFactors

  def self.for(n)
    factors = []
    divisor = 2
    while n > 1 do
        while n % divisor == 0 do
            factors << divisor
            n /= divisor
        end
        divisor += 1
        if divisor ** 2 > n
            if n > 1
              factors << n
              break
            end
        end
    end
    return factors
  end
end
