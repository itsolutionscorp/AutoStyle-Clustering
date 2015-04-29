class PrimeFactors

  def self.for num
    result = []
    divisor = 2
    while num > 1
      while num % divisor == 0
        result << divisor
        num /= divisor
      end
      divisor += 1
    end
    result
  end

end
