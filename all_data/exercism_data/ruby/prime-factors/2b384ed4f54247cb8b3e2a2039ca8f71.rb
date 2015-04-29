class PrimeFactors
  def self.for(num)
    digit = 2
    factors = []

       while num > 1 do
        if num.divmod(digit).last == 0
          factors << digit
          num = num.divmod(digit).first
          redo
        else
          digit += 1
        end
    end
    factors
  end
end
