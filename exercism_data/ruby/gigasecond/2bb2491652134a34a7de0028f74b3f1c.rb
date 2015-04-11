module Gigasecond
  extend self

  def from(date)
    date + days_in(one_gigasecond)
  end

  private

  def one_gigasecond
    1_000_000_000
  end

  def days_in(seconds)
    seconds / seconds_per_day
  end

  def seconds_per_day
    86_400
  end
end
