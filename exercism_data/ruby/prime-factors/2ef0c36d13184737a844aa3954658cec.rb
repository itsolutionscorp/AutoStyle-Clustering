class PrimeFactors
  def self.for(n)
    prime_factors = []
    factor = 2
    while n > 1 do
      while n % factor == 0 do
        prime_factors.push(factor)
        n /= factor
      end
      factor += 1
    end
    prime_factors
  end
end
