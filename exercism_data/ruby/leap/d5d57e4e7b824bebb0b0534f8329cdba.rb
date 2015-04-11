class Year
  class << self
    def leap?(year)
      @year = year
      # it's a leap year if it's divisible by 4 but not 100, or
      # if it's divisible by 400
      (year_divisible_by(4) && !year_divisible_by(100)) ||
        year_divisible_by(400)
    end

    private

    def year_divisible_by(divisor)
      @year % divisor == 0
    end
  end
end
