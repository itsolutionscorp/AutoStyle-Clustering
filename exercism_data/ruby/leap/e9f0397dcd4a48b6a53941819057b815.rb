class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if @year % 400 == 0
      true
    elsif @year % 100 != 0 && @year % 4 == 0
      true
    else
      false
    end
  end
end
