class PrimeFactors

  def self.for(number)
    result = []
    divisor = 2
    while number > 1
      while number % divisor == 0
        result << divisor
        number /= divisor
      end
      divisor += 1
    end
    result
  end
end
