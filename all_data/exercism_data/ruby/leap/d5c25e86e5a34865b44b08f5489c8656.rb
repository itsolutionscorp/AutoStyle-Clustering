require 'date'

class Year
  attr_reader :year

  def initialize(year)
    @date = Date.new(year, 1, 1)
    @year = @date.year

  end

  def leap?
    if year % 4 == 0
       if year % 100 == 0
        year % 400 == 0 ? true : false
      else
        true
      end
    else
      false
    end
  end
end
