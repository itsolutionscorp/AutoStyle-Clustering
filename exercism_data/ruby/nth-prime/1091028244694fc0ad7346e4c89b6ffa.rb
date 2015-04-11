require 'mathn'
require 'set'

## implementation of the algorithm described at
## http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
class ErathostenesSieve
  def self.create max_prime
    sieve = Set.new (2..max_prime)

    (2..max_prime).each{|i|
      next unless sieve.include? i
      break if i * i > max_prime
      sieve.subtract (i*2..max_prime).step(i)
    }

    sieve
  end
end

class Prime
  @@primes = ErathostenesSieve.create(200_000).sort
  def nth n
    raise ArgumentError if n < 1
    @@primes[n - 1]
  end
end
