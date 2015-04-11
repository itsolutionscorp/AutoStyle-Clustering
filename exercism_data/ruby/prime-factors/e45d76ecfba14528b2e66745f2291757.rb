class PrimeFactors
  def self.for(number)
    factors = []
    candidate = 2
    while number > 1
      quotient, remainder = number.divmod candidate
      if remainder.zero?
        number = quotient
        factors << candidate
      else
        candidate += 1
        if 2*candidate > number
          factors << number
          number = 1
        end
      end
    end
    factors
  end
end
