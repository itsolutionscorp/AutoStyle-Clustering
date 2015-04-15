class Sieve
  def initialize(max)
    @ints = (2..max).to_a
  end

  def primes
    primes = []
    while prime = ints.shift
      ints.delete_if {|i| i % prime == 0}
      primes << prime
    end
    primes
  end

private;  attr_reader :ints
end
