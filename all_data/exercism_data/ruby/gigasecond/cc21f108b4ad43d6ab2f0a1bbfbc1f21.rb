require 'date'
require 'time'

class Gigasecond
  
  def self.from(month, day, year)
      origin = Time.new(year, month, day)
      gigadate = origin + (10**9).to_date
      gigadate.strftime('%a %d %b %Y')
  end

end
