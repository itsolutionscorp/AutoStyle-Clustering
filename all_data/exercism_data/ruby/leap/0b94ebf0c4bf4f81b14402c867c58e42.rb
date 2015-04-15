class Year
  def initialize (year)
    @year=year
  end
  attr_reader :year

  def leap?
    divisibleby?(400) || !divisibleby?(100) && divisibleby?(4)
  end

  def divisibleby? ( num )
    year % num == 0
  end
end
