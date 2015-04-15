class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes
    prime = []
    prime_interval = (2..@limit).to_a
      while prime_interval.length > 0
        prime << prime_interval.first
        prime_interval.delete_at(0)
        prime_interval.delete_if { |x| x % prime.last == 0 }
      end
    prime
  end

end
