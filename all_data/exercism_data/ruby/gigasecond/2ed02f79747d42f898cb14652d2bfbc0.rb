require 'date'
require 'time'

class Gigasecond
  class << self
    def from(date)
      @born_at = (date.to_time + 10**9).to_date
    end
  end
end
