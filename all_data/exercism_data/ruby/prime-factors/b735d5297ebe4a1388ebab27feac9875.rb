class PrimeFactors
  def self.for(n)
    factors = []
    factor = 2

    while n > 1 do
      while n % factor == 0 do
        factors << factor
        n /= factor
      end

      factor += 1

      if factor*factor > n
        factors << n if n > 1
        break
      end
    end

    factors
  end
end
