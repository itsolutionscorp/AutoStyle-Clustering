module Year
  extend self

  def leap?(year)
    fourth?(year) && !leap_exclusion?(year)
  end

  private

  def leap_exclusion?(year)
    hundreth?(year) && !four_hundreth?(year)
  end

  def fourth?(year)
    year % 4 == 0
  end

  def four_hundreth?(year)
    year % 400 == 0
  end

  def hundreth?(year)
    year % 100 == 0
  end

end
