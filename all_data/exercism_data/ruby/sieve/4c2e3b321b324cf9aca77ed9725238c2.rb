class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    (2..@limit).map.with_object([]) do |possible_prime, primes|
      primes << possible_prime if primes.none? { |prime| possible_prime % prime == 0 }
    end
  end
end
