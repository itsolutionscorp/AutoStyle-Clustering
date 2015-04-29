require 'date'

class Year
  def self.leap?(year)
    year = year.to_i
    return true if year % 400 == 0
    return false if year % 100 == 0
    year % 4 == 0
  end
end
