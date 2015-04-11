class Year
  class << self
    def leap?(year)
      @year = year

      return true if is_normal_leap_year?
      return true if is_complicated_leap_year?

      return false
    end

    private

    def is_normal_leap_year?
      is_divisible_by_4? && is_not_divisible_by_100?
    end

    def is_complicated_leap_year?
      is_divisible_by_4? && is_divisible_by_100? && is_divisible_by_400?
    end

    def is_divisible_by_4?
      @year % 4 == 0
    end

    def is_not_divisible_by_100?
      @year % 100 != 0
    end

    def is_divisible_by_100?
      @year % 100 == 0
    end

    def is_divisible_by_400?
      @year % 400 == 0
    end
  end
end
