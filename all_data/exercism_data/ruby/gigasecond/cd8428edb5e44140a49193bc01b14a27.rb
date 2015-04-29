require 'date'

module Gigasecond
  def self.from(date)
    (date.to_time + 1E9).to_date
  end
end
