class Year

  def initialize(y)
    @year = y
  end

  def leap?
    if @year % 400 == 0
      return true
    elsif @year % 100 == 0
      return false
    elsif @year % 4 == 0
      return true
    else
      false
    end
  end

end
