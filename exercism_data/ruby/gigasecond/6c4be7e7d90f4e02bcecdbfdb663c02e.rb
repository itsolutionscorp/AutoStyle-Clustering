module Gigasecond
  extend self

  def from(date)
    date + billion_seconds_in_days
  end

  private

  def billion_seconds_in_days
    (1E9 / 60 / 60 / 24).to_i
  end

end
