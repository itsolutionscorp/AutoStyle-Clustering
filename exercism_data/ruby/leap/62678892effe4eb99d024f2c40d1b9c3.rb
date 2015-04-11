class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (every_fourth? && !every_hundredth?) ||
      every_four_hundredth?
  end

  private

  def every_four_hundredth?
    evenly_divisible_by? 400
  end

  def every_fourth?
    evenly_divisible_by? 4
  end

  def every_hundredth?
    evenly_divisible_by? 100
  end

  def evenly_divisible_by?(number)
    @year % number == 0
  end
end
