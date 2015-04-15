require 'date'
class Date
  def leap?
    year = self.year
    year %4 == 0 unless (year %100 == 0 unless year %400 == 0)
  end

  alias :gregorian_leap? :leap?
  alias :julian_leap? :leap?
end

class Year
  def self.leap?(year)
    Date.new(year).leap?
  end
end


Year.leap?(1997)
