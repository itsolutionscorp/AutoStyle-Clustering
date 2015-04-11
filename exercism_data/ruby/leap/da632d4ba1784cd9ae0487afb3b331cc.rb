class Year
  attr_accessor :year

  def initialize(year)
    @year = year
  end

  def leap?
    # self.divisible_by?(400) || ( self.divisible_by?(4) && !self.divisible_by?(100) )
    year % 400 == 0 || ( year % 4 == 0 && year % 100 != 0 )
  end

end
