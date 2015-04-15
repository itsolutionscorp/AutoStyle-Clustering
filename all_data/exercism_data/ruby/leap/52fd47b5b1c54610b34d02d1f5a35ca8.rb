class Year
  def initialize(year)
    @year = year
  end

  def divisible_by?(integer)
    @year % integer == 0
  end

  def leap?
    if !divisible_by?(100)
      divisible_by?(4)
    elsif divisible_by?(400)
      divisible_by?(4)
    end
  end
end
