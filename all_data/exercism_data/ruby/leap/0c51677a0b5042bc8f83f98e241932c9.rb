class Year
  def initialize year
    @year = year
  end

  def leap?
    return false if should_compensate_for_error_of_adding_extra_6_hours?
    should_compensate_for_missing_6_hours?
  end

  private

  # Over a period of four centuries, the accumulated error of adding a leap day every
  # four years amounts to about three extra days
  def should_compensate_for_error_of_adding_extra_6_hours?
    (divisible_by?(100) && !divisible_by?(400))
  end

  # Adding an extra day to the calendar every four years compensates
  # for the fact that a period of 365 days is shorter than a solar year by almost 6 hours.
  def should_compensate_for_missing_6_hours?
    divisible_by?(4)
  end

  def divisible_by? value
    @year % value == 0
  end

end
