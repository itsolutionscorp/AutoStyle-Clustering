require 'date'

module Gigasecond
  DREVIL = 10**9

  def self.from(date)
    Time.at(date.to_time.to_i + DREVIL).to_date
  end
end
