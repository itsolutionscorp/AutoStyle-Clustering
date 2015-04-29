# encoding: utf-8

# Year implementation for exercism 'Year' exercise
class Year
  def initialize(year)
    @year = year
  end

  def leap?
    (multiple_of?(4) && !multiple_of?(100)) || multiple_of?(400)
  end

  private

  def multiple_of?(n)
    @year % n == 0
  end
end
