class PrimeFactors
  def self.for(n)
    prime_factors = []
    possible_factor = 2
    while n >= possible_factor
      if n%possible_factor == 0
        prime_factors << possible_factor
        n = n / possible_factor
      else
        possible_factor += 1
      end
    end
    prime_factors
  end
end
