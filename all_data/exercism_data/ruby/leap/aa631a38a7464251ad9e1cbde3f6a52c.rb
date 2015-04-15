class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if century?
      exceptional_century?
    else
      vanilla_leap?
    end
  end

  private

  def vanilla_leap?
    year_is_divisible_by?(4)
  end

  def century?
    year_is_divisible_by?(100)
  end

  def exceptional_century?
    year_is_divisible_by?(400)
  end

  def year_is_divisible_by?(number)
    @year % number == 0
  end
end
