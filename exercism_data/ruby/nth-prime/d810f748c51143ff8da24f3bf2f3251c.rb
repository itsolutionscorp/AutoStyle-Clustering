class Prime



  class << self


    def nth(n)
      raise ArgumentError if n == 0
      reset_counts
      until @counter == n
        @counter += 1 if check_for_prime(@number)
        @number += 1 unless @counter == n
      end
      @number
    end

    def check_for_prime(number)
      (2...number).each do |divisor|
        return false if number%divisor == 0
      end
      true
    end

    def reset_counts
      @counter = 0
      @number = 2
    end



  end

end
