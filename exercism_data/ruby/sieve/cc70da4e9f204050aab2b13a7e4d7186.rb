class Sieve

  def initialize(limit)
    @limit = limit
  end

  def primes
    range = (2..@limit).to_a
    range.map do |num|
      y = range.index { |x| x >= num }
      prime = range[y]
      multiplier = 2
      while prime * multiplier <= @limit
        range.delete_at(range.index(prime*multiplier)) if range.index(prime*multiplier)
        multiplier += 1
      end
    end
  end

end
