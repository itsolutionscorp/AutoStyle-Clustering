require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    if date.class == Time
      date = date + 1_000_000_000
      date = Date.new(date.year, date.month, date.day)
    else
      gigasecond = (1_000_000_000 / 60 / 60 / 24)
      date = date + gigasecond
    end
    date
  end
end
