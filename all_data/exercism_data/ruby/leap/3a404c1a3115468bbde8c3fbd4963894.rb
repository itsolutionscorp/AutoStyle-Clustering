class Year
  attr_reader :year

  def initialize (year)
    @year = year
  end

  def leap?
    if year % 4 == 0 && year % 400 == 0
      true
    elsif year % 100 == 0
      false
    elsif year % 4 == 0
      true
    else
      false
    end
  end
end
