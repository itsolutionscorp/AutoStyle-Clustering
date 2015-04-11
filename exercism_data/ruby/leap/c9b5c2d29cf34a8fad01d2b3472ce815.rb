class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if century?
      vanilla_leap? && exceptional_century?
    else
      vanilla_leap?
    end
  end

  private

  def vanilla_leap?
    year_mod_is_zero?(4)
  end

  def century?
    year_mod_is_zero?(100)
  end

  def exceptional_century?
    year_mod_is_zero?(400) ? true : false
  end

  def year_mod_is_zero?(number)
    @year % number == 0
  end
end
