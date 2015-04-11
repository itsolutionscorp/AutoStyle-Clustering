class Year

  class << self

    def leap?(year)
      @year = year
      check_leap_year?
    end

    def check_leap_year?
      divisible_by_4? && !divisible_by_100? || divisible_by_4? && leap_century?
    end

    def divisible_by_4?
      boolean_result( @year % 4 )
    end

    def divisible_by_100?
      boolean_result( @year % 100 )
    end

    def divisible_by_400?
      boolean_result( @year % 400 )
    end

    def leap_century?
      divisible_by_100? && divisible_by_400?
    end

    def boolean_result(result)
      result == 0
    end

  end

end
