class Year
  def initialize(year)
    @year = year
  end

  def leap?
    if divisible_by? 400
      # All years divisible by 400 are leap years.
      true
    elsif divisible_by? 4 and not divisible_by? 100
      # The other three even centuries are not leap years,
      # but all other years divisible by 4 are.
      true
    else
      # Everything else is not a leap year.
      false
    end
  end

  private

  def divisible_by?(num)
    @year % num == 0
  end
end
