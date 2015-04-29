class Gigasecond
  def self.from(day)
    Date.parse((day + billion_seconds_in_days).to_s)
  end

  private

  def self.billion_seconds_in_days
    1e9 / seconds_in_day
  end

  def self.seconds_in_day
    24 * 60 * 60
  end
end
