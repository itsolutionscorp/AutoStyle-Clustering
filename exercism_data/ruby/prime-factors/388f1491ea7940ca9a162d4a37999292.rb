class PrimeFactors
  def self.for(n)
    result = []
    divisor = 2
    while(n > 1) do
      if(n % divisor == 0)
        result << divisor
        n = n/divisor
      else
        divisor += 1
      end
    end
    result
  end
end
