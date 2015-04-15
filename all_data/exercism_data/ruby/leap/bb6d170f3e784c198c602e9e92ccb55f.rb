## This code tests years and returns true if the year is a leapyear and
#false if it is not.
#
## Ozzie Gooen, 2014
#
class Year
  def self.leap?(year)

    div_by_4 = (year%4 == 0)
    div_by_100 = (year%100 == 0)
    div_by_400 = (year%400 == 0)

    if div_by_400
      true
    elsif div_by_100
      false
    elsif div_by_4
      true
    end
  end

end
