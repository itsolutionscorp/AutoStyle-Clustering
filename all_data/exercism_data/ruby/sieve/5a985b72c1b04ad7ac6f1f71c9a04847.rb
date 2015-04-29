class Sieve
  attr_reader :limit
  attr_accessor :primes

  def initialize(limit)
    @limit      = limit
    @primes = calculate_primes
  end

  def calculate_primes
    primes     = 2.upto(limit).to_a
    p          = 2
    non_primes = []

    while p
      range          = (p + 1).upto(limit).to_a
      local_marked   = multiples_of_p_in_range(p, range)
      non_primes     = non_primes + local_marked
      primes.delete_if { |n| n % p == 0 }
      local_unmarked = range - local_marked

      p = local_unmarked.first
    end

    non_primes
  end

  private

  def mark_non_primes(p)
  end

  def multiples_of_p_in_range(p, range)
    range.select { |n| n % p == 0 }
  end
end
