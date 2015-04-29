class PrimeFactors
  def self.for(n)
    prime_factors = []
    possible_factor = 2
    while possible_factor**2 <= n
      while n % possible_factor == 0
        prime_factors << possible_factor
        n /= possible_factor
      end
      possible_factor += 1
    end
    prime_factors << n if n > 1
    prime_factors
  end
end
