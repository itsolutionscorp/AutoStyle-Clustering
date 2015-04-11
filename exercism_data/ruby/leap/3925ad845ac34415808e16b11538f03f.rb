class Year
  def initialize(yr)
    @yr = yr
  end
  def leap?
    (@yr % 400 == 0) || (@yr % 4 == 0 && @yr % 100 != 0)
  end
end
