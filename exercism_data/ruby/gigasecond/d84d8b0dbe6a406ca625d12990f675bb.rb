require 'date'
require 'time'

class Gigasecond
  def self.from(dateVal)
    # Convert gigaseconds to number of days
    days = 10**9/86400
    # Use Julian Date and add the days to the date value passed in the method and return the resulting future date
    futuredate = Date.jd(dateVal.jd + days)
  end
end
