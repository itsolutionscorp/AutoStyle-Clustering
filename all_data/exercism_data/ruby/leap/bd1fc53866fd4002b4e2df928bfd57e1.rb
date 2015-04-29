class Year

  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    Year.new(year).leap?
  end


  def leap?
      leapyear = @year % 4 == 0
      leapyear = false if (@year % 100 == 0) && (@year % 400 != 0)
      leapyear
  end

end
