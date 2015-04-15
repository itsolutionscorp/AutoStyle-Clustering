require "prime"

class PrimeFactors
  def self.for(n)
    factors = []
    primes = Prime.to_enum
    prime = primes.next

    while n > 1
      if n % prime == 0
        factors << prime
        n /= prime
      else
        prime = primes.next
      end
    end

    factors
  end
end
