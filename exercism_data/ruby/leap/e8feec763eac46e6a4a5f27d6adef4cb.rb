class Year
  def initialize(year)
    @year = year
  end

  def leap?
    return true if divisible_by?(400)
    return true if divisible_by?(4) && !divisible_by?(100)
    false
  end

  def divisible_by?(i)
    @year % i == 0
  end
end


# ```plain
# on every year that is evenly divisible by 4
#   except every year that is evenly divisible by 100
#     unless the year is also evenly divisible by 400
# ```
