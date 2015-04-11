class Year
  def initialize(year)
    @year = year
  end

  def leap?
    case
    when @year % 400 == 0
      true
    when (@year % 4 == 0 unless @year % 100 == 0)
      true
    else
      false
    end
  end
end
