class Sieve
  def initialize(limit)
    @limit = limit
  end

  def primes
    primes = []
    candidates = *(2..@limit)

    until candidates.empty?
      primes << candidates.shift
      candidates.delete_if { |e| e % primes.last == 0 }
    end
    primes
  end
end
