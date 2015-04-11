class Year
  def initialize(year)
    @year = year
  end

  def leap?
    year = @year
    if year.modulo(100).zero?
      year.modulo(400).zero?
    else
      year.modulo(4).zero?
    end
  end
end
