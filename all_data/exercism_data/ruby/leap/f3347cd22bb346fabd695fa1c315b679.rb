# Write a program that will take a year and report if it is a leap year.

# The tricky thing here is that a leap year occurs:

# ```plain
# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400
# ```

# For example, 1997 is not a leap year, but 1996 is.  1900 is not a leap
# year, but 2000 is.

# If your language provides a method in the standard library that does
# this look-up, pretend it doesn't exist and implement it yourself.

class Year

  def self.leap?(year)
    if year % 4 == 0
      if year % 100 == 0 && year % 400 != 0
        not_leap_year(year)
        false
      else
        leap_year(year)
        true
      end
    else
      not_leap_year(year)
      false
    end 
  end

  def self.leap_year(year)
    "Yes, #{year} is a leap year" 
  end

  def self.not_leap_year(year)
    "No, #{year} is not a leap year" 
  end
end
