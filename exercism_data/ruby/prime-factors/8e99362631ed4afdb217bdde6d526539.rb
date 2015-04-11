#!/usr/bin/env ruby

# Exercism 21
# Prime Factors

class PrimeFactors

  def self.for(num)
    primes, divisor = [], 1

    while num > 1 and divisor += 1
      primes << divisor and num /= divisor while num % divisor == 0
    end
    primes
  end

end
