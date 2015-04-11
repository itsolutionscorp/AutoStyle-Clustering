class Sieve
  attr_reader :primes

  def initialize(n)
    @primes = sieve(n)
  end

  private

  def divisible(numerator, denominator)
    numerator % denominator == 0
  end

  def sieve(n)
    possibilities = (2..n).to_a
    primes = []

    while possibilities.length > 0 do
      prime = possibilities.shift
      primes << prime
      possibilities = possibilities.select { |possibility|
        !divisible(possibility, prime)
      }
    end

    primes
  end
end
