require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    datetime = date.to_time
    (datetime + 10**9).to_date
  end
end
