class Sieve
  attr_reader :limit, :ints

  def initialize(limit)
    @limit = limit
    @ints = Array.new(limit, true)
    @ints[0..1] = [false, false]
  end

  def primes
    calculate_primes
    @ints.map.with_index { |is_prime, n| n if is_prime }.compact
  end

  private

  def calculate_primes
    2.upto(limit**0.5) do |i|
      next unless @ints[i]
      k = 0
      j = (i**2)
      while j < limit
        @ints[j] = false
        k += 1
        j = (i**2) + (k * i)
      end
    end
  end
end
