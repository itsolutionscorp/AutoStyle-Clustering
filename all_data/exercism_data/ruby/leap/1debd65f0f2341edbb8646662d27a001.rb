class Year
  def initialize(year)
    @year = year
  end

  def leap?
    year = @year
    if year.modulo(100).zero?
      return year.modulo(400).zero?
    else
      return year.modulo(4).zero?
    end
  end
end
