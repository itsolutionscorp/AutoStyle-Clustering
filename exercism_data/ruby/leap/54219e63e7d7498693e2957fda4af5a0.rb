class Year

  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    Date.leap?(year)
  end

end
