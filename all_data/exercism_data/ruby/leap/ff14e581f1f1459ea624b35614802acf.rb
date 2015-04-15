#!/usr/bin/env ruby

# Exercism 9
# Leap Year

class Year

  def self.leap?(year)

    year % 4 != 0 ? false : true unless year % 100 == 0 and not year % 400 == 0

  end

end
