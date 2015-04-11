class Year

  class << self
    def leap?(year)
      (year % 4 == 0) && !leap_exception?(year)
    end

    def leap_exception?(year)
      (year % 100 == 0) && !(year % 400 == 0)
    end
  end

end
