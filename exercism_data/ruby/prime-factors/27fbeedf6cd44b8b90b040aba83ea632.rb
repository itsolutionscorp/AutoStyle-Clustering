class PrimeFactors
  def self.for(num)
    digit = 2
    factors = []

       while num > 1 do
        while num % digit == 0
          factors << digit
          num /= digit
        end
          digit += 1
    end
    factors
  end
end
