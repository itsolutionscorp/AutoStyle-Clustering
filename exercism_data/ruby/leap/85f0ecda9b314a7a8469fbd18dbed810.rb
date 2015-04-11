class Year

  def self.leap?(year)
    is_leap_year = false
    if year % 400 == 0
      is_leap_year = true
    elsif year % 100 == 0
      is_leap_year = false
    elsif year % 4 == 0
      is_leap_year = true
    end
    is_leap_year
  end

end


# ```plain
#on every year that is evenly divisible by 4
#  except every year that is evenly divisible by 100
#    unless the year is also evenly divisible by 400
#```
