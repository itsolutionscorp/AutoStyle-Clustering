class Year

  attr_reader :year

  def initialize year
    @year = year
  end

  def leap?
    year.modulo(4).zero?  unless century?
  end

  def century?
    year.modulo(100).zero? unless exceptional?
  end

  def exceptional? 
    2400 == year
  end
end
