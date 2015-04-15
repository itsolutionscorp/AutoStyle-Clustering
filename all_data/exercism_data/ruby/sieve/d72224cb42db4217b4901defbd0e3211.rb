class Sieve

  attr_reader :max
  def initialize(max)
    @max = max
    @ints = ints_hash
  end

  def primes
    primes_array = []
    while prime = ints.shift
      delete_multiples(prime[0])
      primes_array << prime[0]
    end
    primes_array
  end

private
  attr_reader :ints
  def ints_hash
    (2..max).inject({}) {|ints,i| ints[i] = 1; ints }
  end

  def delete_multiples(prime)
    ints.delete_if {|i,v| i % prime == 0}
  end

  def next_prime(ints)
    ints.empty? ? max + 1 : ints.shift.first
  end
end

# This solution also passes these tests for "edge" cases
#  def test_2
#    expected = [2]
#    assert_equal expected, Sieve.new(2).primes
#  end
#
#  def test_range_max_is_a_prime
#    expected = [2, 3, 5, 7, 11]
#    assert_equal expected, Sieve.new(11).primes
#  end
