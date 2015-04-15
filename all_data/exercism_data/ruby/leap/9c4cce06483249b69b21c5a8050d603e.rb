class Year
  def initialize(yr)
    @year = yr
  end

  def leap?
    case
    when (@year % 400) == 0
      true
    when (@year % 100) == 0
      false
    when (@year % 4) == 0
      true
    else
      false
    end
  end
end
