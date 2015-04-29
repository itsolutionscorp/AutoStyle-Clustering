require 'date'

class Date
  def leap?
    case
    when year % 400 == 0
      true
    when year % 100 == 0
      false
    when year % 4 == 0
      true
    end
  end

  alias_method :gregorian_leap?, :leap?
  alias_method :julian_leap?, :leap?
end

class Year
  def self.leap? year
    Date.new(year).leap?
  end
end
