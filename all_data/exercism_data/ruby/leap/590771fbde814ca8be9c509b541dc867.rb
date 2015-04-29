class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if @year.modulo(400).zero?
    return true if @year.modulo(4).zero? && @year.modulo(100) != 0
    false
  end 

end
