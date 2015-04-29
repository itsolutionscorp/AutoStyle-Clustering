require 'date'

module Gigasecond
  SECONDS_PER_GIGASECOND = 10**9
  def self.from(date)
    (date.to_time + SECONDS_PER_GIGASECOND).to_date
  end
end
