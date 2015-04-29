class PrimeFactors

  def self.for number
    divisor = 2

    final_result = []

    while divisor <= number
      if number % divisor == 0
        final_result << divisor
        number /= divisor
      else
        divisor += 1
      end
    end
    final_result
  end
end
