module Gigasecond
  SECONDS_TO_ADD = 1_000_000_000

  def self.from(date_or_time)
    (date_or_time.to_time + SECONDS_TO_ADD).to_date
  end
end
