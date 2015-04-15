class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    meets_vanilla_criteria? && !omitted_century?
  end

  private

  def meets_vanilla_criteria?
    year % 4 == 0
  end

  def omitted_century?
    century? && !quad_century?
  end

  def century?
    year % 100 == 0
  end

  def quad_century?
    year % 400 == 0
  end

end
