class Year

  attr_accessor :year

  def initialize(year)
    self.year = year
  end

  def leap?
    case
    when (self.year % 100) == 0
      (self.year % 400 == 0)
    else
      (self.year % 4 == 0)
    end
  end

end
