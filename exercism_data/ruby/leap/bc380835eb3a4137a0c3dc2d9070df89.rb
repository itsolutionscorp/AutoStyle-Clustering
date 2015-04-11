class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def leap?
    year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 )
  end

end
