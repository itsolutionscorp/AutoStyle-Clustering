module PrimeFactors
  
  def self.for(num)
    ary, factor = [], 2

    while  num > 1
      while num % factor == 0
        ary << factor
        num /= factor
      end
      factor += 1
    end
    ary
  end
end
