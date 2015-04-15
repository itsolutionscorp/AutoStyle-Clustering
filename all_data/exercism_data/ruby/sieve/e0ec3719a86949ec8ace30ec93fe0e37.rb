class Sieve
  def initialize(max)
    @max = max
    @is_prime = Array.new(max, true)
    # Only need to search up to sqrt(max) to find all primes smaller than max
    @stop = Math.sqrt(max).ceil

    fill
  end

  attr_reader :max, :is_prime, :stop

  def fill
    is_prime[0] = false
    is_prime[1] = false
    prime = 2
    while prime < stop
      strike_out_factors_of(prime)
      prime = next_prime(prime)
    end
  end

  def strike_out_factors_of(prime)
    (prime * 2 .. max - 1).step(prime) do |n|
      is_prime[n] = false
    end
  end

  def next_prime(prime)
    begin
      prime += 1
    end until is_prime[prime]
    prime
  end

  def primes
    primes = []
    is_prime.each_with_index do |is_prime, index|
      primes << index if is_prime
    end
    primes
  end
end
