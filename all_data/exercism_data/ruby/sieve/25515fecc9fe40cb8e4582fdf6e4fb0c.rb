class Sieve

  attr_reader :max
  def initialize(max)
    @max = max
  end

  def primes
    ints = ints_builder
    primes_array = []
    while prime = ints.shift
      ints.delete_if {|i| i % prime == 0}
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
