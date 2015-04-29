require 'prime'

class PrimeFactors
  def self.for(n)
    primes = Prime.each(n / 2 + 2).to_a
    results = []
    until primes == []
      if n % primes.first == 0
        results << primes.first
        n /= primes.first
      else
        primes = primes[1..-1]
      end
    end
    results
  end
end
