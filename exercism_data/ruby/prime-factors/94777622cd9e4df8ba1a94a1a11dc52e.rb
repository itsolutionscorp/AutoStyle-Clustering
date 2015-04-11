class PrimeFactors
  def self.for(num)
    array = []
    div = 2
    while num > 1
      while (num % div) == 0
        array << div
        num /= div
      end
      div += 1
    end
    array
  end
end
