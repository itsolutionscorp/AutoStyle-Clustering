require 'date'
require 'time'

class Gigasecond
  class << self
    def from(date)
      anniversary_timestamp   = date.to_time.to_i + 10**9
      anniversary_date_string = Time.at(anniversary_timestamp).strftime('%Y-%m-%d')
      Date.parse anniversary_date_string
    end
  end
end
