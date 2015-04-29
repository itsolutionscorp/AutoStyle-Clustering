class Year
  class << self
    def leap?(year)
      is_leap = (year % 4 == 0)
      is_century = (year % 100 == 0)
      is_fourth_century = (year % 400 == 0)

      is_leap && (is_fourth_century || !is_century)
    end
  end
end
