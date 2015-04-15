require 'date'

class Year
  def initialize year
    @year= Date.new year
  end

  def leap?
    @year.leap?
  end
end
