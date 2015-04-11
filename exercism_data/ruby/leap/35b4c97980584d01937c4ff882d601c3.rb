class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    if year % 4 == 0
      if year % 100 == 0
        return year % 400 == 0 ? true : false
      end
      true
    end
  end
end
