# year has a method that allows you to check
# whether a year is a leap year or not
class Year
  # returns true if the year is a leap year
  def self.leap?(year)
    case
    when (year % 400).zero? then true
    when (year % 100).zero? then false
    when (year % 4).zero? then true
    else
      false
    end
  end
end
