require 'date'

class Year
  def initialize(year)
    @year = Date.new(year, 1, 1)
  end

  def leap?
    @year.leap?
  end
end
