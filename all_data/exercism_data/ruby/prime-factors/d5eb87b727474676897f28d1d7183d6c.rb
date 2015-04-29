class PrimeFactors
  class << self
    def for(max_num)
      return [] if max_num == 1

      result = []
      num = 2

      while max_num > 1 do
        if max_num % num == 0
          result.push(num)
          max_num /= num
          num = 2
        else
          num += 1
        end
      end
      
      result.sort
    end
  end
end
