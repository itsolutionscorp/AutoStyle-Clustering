require 'date'

class Date
  def leap?
    case
    when year % 400 == 0 then true
    when year % 100 == 0 then false
    when year % 4   == 0 then true
    end
  end
end

class Year
  def self.leap? year
    Date.new(year).leap?
  end
end
