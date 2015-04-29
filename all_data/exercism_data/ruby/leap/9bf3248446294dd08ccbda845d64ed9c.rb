require 'Date'

class Year

  def self.leap?(year)
    (year % 4 == 0? true : false) unless year % 100 == 0 && year % 400 != 0
  end

end
