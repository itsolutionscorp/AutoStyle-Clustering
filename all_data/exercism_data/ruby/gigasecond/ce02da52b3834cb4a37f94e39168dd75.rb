module Gigasecond
  require 'date'

  SECONDS_PER_GIGASECOND = 10**9

  def self.from(date_or_time)
    (date_or_time.to_time + SECONDS_PER_GIGASECOND).to_date
  end
end
