class Prime
  def self.nth(number)
    raise ArgumentError if number == 0

    PrimeSieve.new.primes[number - 1]
  end
end

class PrimeSieve
  def initialize(top: 200_000)
    @top = top
  end

  def primes
    initialize_primes_array
    sieve_primes

    @primes
  end

  private

  def initialize_primes_array
    @primes = Array.new(2) + (2..@top).to_a
  end

  def sieve_primes
    (2..Math.sqrt(@top)).each do |n|
      next unless @primes[n]

      set_multiples_to_nil(n)
    end

    @primes.compact!
  end

  def set_multiples_to_nil(number)
    (number*number).step(@top, number) do |multiplicand|
      @primes[multiplicand] = nil
    end
  end
end
