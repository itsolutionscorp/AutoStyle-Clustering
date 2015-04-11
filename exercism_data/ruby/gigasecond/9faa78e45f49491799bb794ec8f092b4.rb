require 'date'

module Gigasecond
  def self.from(time)
    (time.to_time + 1_000_000_000).to_date
  end
end
