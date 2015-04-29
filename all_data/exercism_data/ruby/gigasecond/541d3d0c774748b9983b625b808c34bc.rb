require 'date'

module Gigasecond
  def self.from(date_or_time)
    if date_or_time.is_a? Date
      date_or_time + (10**9)/(60*60*24)
    else
      (date_or_time + 10**9).to_date
    end
  end
end
