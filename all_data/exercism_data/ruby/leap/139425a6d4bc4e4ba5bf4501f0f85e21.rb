# Leap years are lots of fun
class Year
  def self.leap?(year)
    # a leap year is divisible by 4, but not 100 unless it is also divisible by 400
    (((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0)
  end
end
