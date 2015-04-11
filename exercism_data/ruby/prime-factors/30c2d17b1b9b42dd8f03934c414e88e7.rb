class PrimeFactors
  def self.for(number)
    factors = []
    dividend = number
    factor = 2

    while factor <= dividend
      quotient, modulus = dividend.divmod(factor)

      if modulus.zero?
        factors << factor
        dividend = quotient
      else
        factor += 1
      end
    end

    factors
  end
end
