class Year
  attr_reader :year
  
  def initialize(year)
    @year = year
  end

  def leap?
    ((@year%4 == 0 and @year%100 != 0) or @year%400 == 0)
  end
end
