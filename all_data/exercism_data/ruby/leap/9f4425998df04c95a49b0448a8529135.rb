# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400

class Year
  def self.leap?(year)
    (year % 4 == 0) and (year % 100 != 0) || (year % 400 == 0) ? true : false
  end
end
