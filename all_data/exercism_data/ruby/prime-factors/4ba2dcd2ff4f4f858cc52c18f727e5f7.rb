module PrimeFactors
  def self.for n
    factors = []
    factor = 2
    while n > 1
      while n % factor == 0
        factors << factor
        n /= factor
      end
      factor += 1
    end
    factors
  end
end
