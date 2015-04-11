class Year
  def initialize year
    @year = year
  end

  def leap?
    if century?
      divisible_for?(400)
    else
      divisible_for?(4)
    end
  end

private
  def century?
    divisible_for? 100
  end

  def divisible_for? divider
    @year % divider == 0
  end
end
