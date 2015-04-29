class PrimeFactors
  def self.for(n)
    self.new(n).prime_factors
  end

  attr_accessor :n, :primes, :factors
  def initialize(n)
    @n = n
    @primes = [2]
    @factors = []
  end

  def prime_factors
    while n > 1
      factor_n(primes.last)
    end
    factors
  end

  def factor_n(x)
    while self.n % x == 0
      self.factors << x
      self.n /= x
    end
    self.primes << next_prime
  end

  def next_prime
    candidate = primes.last + 1
    until is_prime?(candidate)
      candidate += 1
    end
    candidate
  end

  def is_prime?(y)
    primes.each do |p|
      return false if y % p == 0
    end
    true
  end

end
