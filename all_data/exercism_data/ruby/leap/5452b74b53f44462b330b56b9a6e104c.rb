require 'delegate'

class Year < SimpleDelegator

  def self.leap?(number)
    year = Year.new(number)
    year % 400 == 0 || (year % 4 == 0 && !(year % 100 ==0))
  end

end
