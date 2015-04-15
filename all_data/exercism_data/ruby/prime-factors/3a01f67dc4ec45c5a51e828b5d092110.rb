class PrimeFactors

  def self.for(num)
    arr = []
    i = 2
    while num > 1
      if num % i == 0
        num /= i
        arr << i 
      else
        i+=1
      end
    end
    arr
  end

end
