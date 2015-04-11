# PrimeFactors by Uncle Bob

class PrimeFactors
  def self.for n
    factors, divisor = [], 1
    while n > 1 and divisor += 1
      factors << divisor and n/=divisor while n%divisor == 0
      divisor = n - 1 if divisor > Math.sqrt(n)
    end
    factors
  end
end
