class Sieve
  def initialize(max)
    @max = max
    @sieve = Array.new(max, true)
    # Only need to search up to sqrt(max) to find all primes smaller than max
    @stop = Math.sqrt(max).ceil

    fill
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
