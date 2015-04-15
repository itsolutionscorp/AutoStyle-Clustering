class Year
  def initialize(yr)
    @yr = yr
  end

  def leap?
    is_regular? && (not_hundred? || four_hundred?)
  end

  private

  def is_regular?
    @yr % 4 == 0
  end

  def not_hundred?
    @yr % 100 != 0
  end

  def four_hundred?
    @yr % 400 == 0
  end
end
