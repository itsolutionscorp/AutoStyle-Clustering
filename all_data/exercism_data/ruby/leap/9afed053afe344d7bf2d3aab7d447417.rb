class Year

  def initialize(year)
    @year = year
  end

  def leap?
   divisible_by?(4) && (!century? || divisible_by?(400))
  end

  def divisible_by?(number)
    @year % number == 0
  end

  def century?
    @year % 100 == 0
  end
end
