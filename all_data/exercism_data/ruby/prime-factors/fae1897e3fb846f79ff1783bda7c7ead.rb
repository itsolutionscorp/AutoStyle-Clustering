class PrimeFactors
  def self.for number
    factors = []
    divisor = 2
    while number != 1 do
      if number%divisor == 0 then
        factors << divisor
        number /= divisor
      else
        divisor += 1
      end
    end
    factors
  end
end
