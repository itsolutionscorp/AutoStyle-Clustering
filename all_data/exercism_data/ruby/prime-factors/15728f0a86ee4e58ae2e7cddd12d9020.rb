class PrimeFactors

  def self.for(number)
    factors = []
    i = 2

    while i <= number
      while number % i == 0
        factors << i
        number = number / i
      end
      i += 1
    end

    factors
  end

end
