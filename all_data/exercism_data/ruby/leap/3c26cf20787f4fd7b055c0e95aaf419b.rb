# A leap year occurs on every year that is evenly divisible by 4
# except every year that is evenly divisible by 100
# unless the year is also evenly divisible by 400
class Year

  def self.leap?(year)
    (year % 4 == 0) && century_exception?(year)
  end

  def self.century_exception?(year)
    (year % 100 != 0) || (year % 400 == 0)
  end
end
