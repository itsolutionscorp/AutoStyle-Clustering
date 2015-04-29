require 'date'

class Year

  def initialize(year)
    @year = year
  end

  def leap?
    Date.gregorian_leap? @year
  end

end
