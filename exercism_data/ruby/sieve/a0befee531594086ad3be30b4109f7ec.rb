class Sieve

  attr_reader :max
  def initialize(max)
    @max = max
    @ints = ints_builder
  end

  def primes
    primes_array = []
    while prime = ints.shift
      delete_multiples(prime)
      primes_array << prime
    end
    primes_array
  end

private
  attr_reader :ints
  def ints_builder
    (2..max).to_a
  end

  def delete_multiples(prime)
    ints.delete_if {|i| i % prime == 0}
  end
end
