#! /usr/bin/env ruby
#

class Year
  def initialize(year)
    @year = year
  end

  # decide if it is a leap year
  #
  # on every year that is evenly divisible by 4
  #  except every year that is evenly divisible by 100
  #    unless the year is also evenly divisible by 400
  def leap?
    ((@year % 4) == 0 && (@year % 100) != 0) || (@year % 400) == 0
  end
end
