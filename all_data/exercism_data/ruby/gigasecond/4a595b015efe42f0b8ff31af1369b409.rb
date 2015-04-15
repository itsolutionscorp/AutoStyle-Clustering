require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    gs = 10 ** 9
    end_date = date.to_time + gs
    end_date.to_date
  end
end
