class Year
  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    divided_by?(4) and not divided_by?(100) or divided_by?(400)
  end

  private

  def divided_by? number
    year % number == 0
  end
end
