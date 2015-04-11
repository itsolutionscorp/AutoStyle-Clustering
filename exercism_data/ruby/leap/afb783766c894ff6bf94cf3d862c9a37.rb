class Year
  def initialize(year)
    @year = year
  end

  def leap?
    fourth_century? or fourth_year? && !century?
  end

  private
  attr_accessor :year

  def fourth_year?
    year.modulo(4).zero?
  end

  def century?
    year.modulo(100).zero?
  end

  def fourth_century?
    year.modulo(400).zero?
  end
end
