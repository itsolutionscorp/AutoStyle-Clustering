class Year
  attr_reader :year

  def initialize(year)
    @year = year
  end

  def leap?
    return true if exceptional_century?
    multiple_of_four? && !century?
  end

  private

  def multiple_of_four?
    year % 4 == 0
  end

  def century?
    year % 100 == 0 
  end

  def exceptional_century?
    year % 400 == 0
  end

end
