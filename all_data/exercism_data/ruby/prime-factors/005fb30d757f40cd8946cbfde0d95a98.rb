require "prime"

class PrimeFactors
  def self.for(n)
    remaining = n
    Prime.each_with_object([]) { |prime, prime_factors|
      break prime_factors if remaining == 1
      while remaining % prime == 0
        prime_factors << prime
        remaining /= prime
      end
    }
  end
end
