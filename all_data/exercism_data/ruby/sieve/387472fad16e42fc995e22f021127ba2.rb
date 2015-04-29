class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes
    (2..@limit).select { |n| (2..(n-1)).none? { |d| n % d == 0 } }
  end

end
