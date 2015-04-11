require 'date'

class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    Date.new(year, 1, 1).leap?
  end
end
