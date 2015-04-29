require 'prime'

class PrimeFactors
  def self.for(n)
    return [] if n == 1
    primes = Prime.take_while{ |p| p <= n }
    results = []

    i = 0
    loop do
      if (n % primes[i]).zero?
        results << primes[i]
        n = n / primes[i]
      else
        i += 1
      end

      break if primes[i] > n
    end

    results
  end
end
