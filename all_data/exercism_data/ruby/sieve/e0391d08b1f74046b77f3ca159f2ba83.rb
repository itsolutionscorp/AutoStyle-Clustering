class Sieve
  attr_reader :primes

  def initialize(n)
    @primes = (2..n).to_a
    mark(2)
  end

  def mark(prime)
    return if prime >= @primes.last
    multiple = prime

    loop do
      @primes.delete(multiple += prime)
      break if multiple >= @primes.last
    end

    next_prime = @primes.at(@primes.index(prime) + 1)
    mark(next_prime)

  end

end
