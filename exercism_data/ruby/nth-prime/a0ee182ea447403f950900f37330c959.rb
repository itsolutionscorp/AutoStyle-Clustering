class Sieve
  attr_reader :primes
  def initialize(max)
    @primes = []

    universe = (2..max).to_a
    while prime = universe.shift
      @primes << prime
      universe.select! {|i| i % prime != 0}
    end
  end
end

class Prime
  Primes = Sieve.new(104743).primes
  def self.nth(x)
    raise ArgumentError if x == 0
    Primes[x - 1]
  end
end
