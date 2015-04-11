class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    @primes ||= calculate_primes
  end

  private

  def calculate_primes
    divisor = 2
    while divisor
      cross_out(divisor)
      divisor = find_next_prime(divisor)
    end
    sieve_to_ints
  end

  def sieve
    @sieve ||= Array.new(@limit + 1) { true }
  end

  def find_next_prime(n)
    n += 1
    while n <= @limit
      return n if sieve[n]
      n += 1
    end
  end

  def cross_out(prime)
    multiple = prime * 2
    while multiple <= @limit
      sieve[multiple] = nil
      multiple += prime
    end
  end

  def sieve_to_ints
    primes = []
    i = 2
    while i < sieve.length
      primes << i if sieve[i]
      i += 1
    end
    primes
  end
end
