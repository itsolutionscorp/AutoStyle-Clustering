class Year
  def self.leap?(year)
    divisible_by_4 = (year % 4 == 0)
    divisible_by_100 = (year % 100 == 0)
    divisible_by_400 = (year % 400 == 0)

    divisible_by_400 || divisible_by_4 && !divisible_by_100
  end
end
