class Year
  attr_reader :year

  def initialize(year)
    @year = year.to_i
  end

  def leap?
    (regular? && !century?) || fourth_century?
  end

  private

  def regular?
    year % 4 == 0
  end

  def century?
    year % 100 == 0
  end

  def fourth_century?
    year % 400 == 0
  end
end
