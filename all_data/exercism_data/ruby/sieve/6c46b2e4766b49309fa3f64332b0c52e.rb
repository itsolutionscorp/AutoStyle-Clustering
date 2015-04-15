class Sieve
  def initialize(upper_bound)
    @upper_bound = upper_bound
  end

  def primes
    return @primes if @primes
    numbers = (2..@upper_bound).to_a
    @primes = []
    while(new_prime = numbers.shift)
      @primes << new_prime
      numbers = sieve(primes.last, numbers)
    end
    @primes
  end

  private

  def sieve(prime, numbers)
    numbers.reject { |x|  x % prime == 0 }
  end
end
