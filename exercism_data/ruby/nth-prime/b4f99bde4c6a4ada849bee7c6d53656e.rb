class Prime
  def self.nth(count)
    raise ArgumentError, "count must be greater than 0" unless count > 0

    if count > 6
      max = upper_bound_for_nth_prime(count)
    else
      max = 15
    end

    sieve = SieveOfEratosthenes.new(max)
    sieve.fill
    sieve.primes[count - 1]
  end

  # According to Rosser's theorem, the upper bound for the nth prime is
  # n * ln n + n * ln ln n
  # http://en.wikipedia.org/wiki/Rosser%27s_theorem
  def self.upper_bound_for_nth_prime(count)
    (count * Math.log(count) + count * Math.log(Math.log(count))).ceil
  end
end

class SieveOfEratosthenes
  def initialize(max)
    @max = max
    @sieve = Array.new(max, true)
    # Only need to search up to sqrt(max) to find all primes smaller than max
    @stop = Math.sqrt(max).ceil
  end

  attr_reader :max, :sieve, :stop

  def fill
    sieve[0] = false
    sieve[1] = false
    current = 2
    while current < stop
      strike_out_factors_of(current)
      current = next_prime(current)
    end
  end

  def strike_out_factors_of(current)
    (current * 2 .. max - 1).step(current) do |n|
      sieve[n] = false
    end
  end

  def next_prime(current)
    begin
      current += 1
    end until sieve[current]
    current
  end

  def primes
    primes = []
    sieve.each_with_index do |is_prime, index|
      primes << index if is_prime
    end
    primes
  end
end
