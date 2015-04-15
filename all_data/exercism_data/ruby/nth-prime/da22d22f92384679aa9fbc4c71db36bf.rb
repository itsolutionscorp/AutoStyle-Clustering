require 'set'

class Prime
  def self.nth(n)
    self.primes(n)[-1]
  end

  def self.is_prime(num, allSmallerPrimes)
    return allSmallerPrimes.detect {|prime| num % prime == 0} == nil
  end

  def self.primes(n)
    raise ArgumentError if n <= 0
    primes = []
    current = 2
    while primes.length < n do
      primes.push(current) if self.is_prime(current, primes)
      current += 1
    end
    primes 
  end
end
