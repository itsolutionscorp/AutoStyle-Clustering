# Utility class to check whether given year is a leap year
class Year
  def self.leap?(year)
    raise ArgumentError unless year.is_a? Integer

    ((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)
  end
end
