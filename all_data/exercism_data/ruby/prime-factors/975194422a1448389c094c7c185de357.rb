class PrimeFactors
  def self.for(n)
    factors = []
    divisor = 2
    while n > 1
      while n.modulo(divisor).zero?
        factors << divisor
        n /= divisor
      end
      divisor += 1
    end
    factors
  end
end
