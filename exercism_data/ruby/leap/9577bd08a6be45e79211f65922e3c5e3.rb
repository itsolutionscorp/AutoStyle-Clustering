class Year
  def initialize(year)
    @year = year
  end

  def leap?
    case
    when year_is_divisible_by(400)
      true
    when year_is_divisible_by(100)
      false
    when year_is_divisible_by(4)
      true
    end
  end

  def year_is_divisible_by(factor)
    @year % factor == 0
  end
end
