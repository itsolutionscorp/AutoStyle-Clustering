class Sieve
  attr_reader :limit
  attr_reader :numbers
  attr_reader :primes

  def initialize(limit)
    @limit      = limit
    @numbers    = 2.upto(limit).to_a
    @primes     = calculate_primes
  end

  private

  def calculate_primes
    numbers - mark_non_primes(p)
  end

  def mark_non_primes(p)
    p          = 2
    non_primes = []

    while p
      range          = (p + 1).upto(limit).to_a
      local_marked   = multiples_of_p_in_range(p, range)
      non_primes     = non_primes + local_marked
      local_unmarked = range - local_marked

      p = local_unmarked.first
    end

    non_primes
  end

  def multiples_of_p_in_range(p, range)
    range.select { |n| n % p == 0 }
  end
end