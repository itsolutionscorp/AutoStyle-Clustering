require 'date'
require 'time'

class Gigasecond

  GSinDays = 11574

  def self.from(date)
	date = date.to_date + 1 if date.kind_of?(Time)
	date+= GSinDays
	date
  end
end
