class Year
  def initialize year
    @year = year
  end

  def leap?
    century? ? divisible_for?(400) : divisible_for?(4)
  end

private
  def century?
    divisible_for? 100
  end

  def divisible_for? divider
    @year % divider == 0
  end
end
