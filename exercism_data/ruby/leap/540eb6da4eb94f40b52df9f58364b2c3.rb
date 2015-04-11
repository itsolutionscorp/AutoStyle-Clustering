class Year

  def initialize(year)
    @year = year
  end

  def leap?
    leap_year? and (exceptional_century? or not century?)
  end

  private

  def century?
    devisible_by?(100)
  end

  def exceptional_century?
    devisible_by?(400)
  end

  def leap_year?
    devisible_by?(4)
  end

  def devisible_by?(number)
    @year % number == 0
  end

end
