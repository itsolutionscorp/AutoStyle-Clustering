class Year

  class << self

    def leap?(year)
      @year = year
      check_leap_year?
    end
    
    private

    def check_leap_year?
      divisible_by?(4) && !divisible_by?(100) || leap_century?
    end
    
    def divisible_by?(num)
      boolean_result( @year % num )
    end

    def leap_century?
      divisible_by?(100) && divisible_by?(400)
    end

    def boolean_result(result)
      result == 0
    end

  end

end
