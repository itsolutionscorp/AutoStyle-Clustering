class Year
  def initialize year
    @year = year
  end

  def leap?
    fourth? and (!century? or exceptional_century?)
  end

  private
  def fourth?
    @year % 4 == 0
  end

  def century?
    @year % 100 == 0
  end

  def exceptional_century?
    @year % 400 == 0
  end
end
