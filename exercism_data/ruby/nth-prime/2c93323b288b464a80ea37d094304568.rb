module FactorRefinement
  refine Integer do
    def divides?(n)
      n % self == 0
    end
  end
end

module Prime
  using FactorRefinement

  def self.nth(n)
    raise ArgumentError, "n must be a positive integer" unless n >= 1

    primes_found = [2]
    candidate = 3
    while primes_found.size < n do
      if primes_found.none? { |p| p.divides? candidate }
        primes_found.push candidate
      end
      candidate += 2
    end
    primes_found.last
  end

end
