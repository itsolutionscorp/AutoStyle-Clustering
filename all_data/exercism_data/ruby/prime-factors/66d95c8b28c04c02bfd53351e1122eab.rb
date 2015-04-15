class PrimeFactors
  def self.for(number)
    factors = []
    candidate = 2
    while number > 1
      a,r = number.divmod candidate
      if r.zero?
        number = a
        factors << candidate
      else
        candidate += 1
        if 2*candidate == number+1
          factors << number
          number = 1
        end
      end
    end
    factors
  end
end
