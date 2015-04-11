require 'date'

class Date
  def leap?
    # new implementation
    case
    when year % 400 == 0
      true
    when year % 100 == 0
      false
    when year % 4 == 0
      true
    end
  end

  alias :gregorian_leap? :leap?
  alias :julian_leap? :leap?
end

class Year
  def self.leap?(year)
    Date.new(year).leap?
  end
end
