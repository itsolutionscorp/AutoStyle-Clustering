require 'date'
require 'time'

class Gigasecond
  # return the date a gigasecond from now
  def self.from(date)
    # how many days are a gigasecond
    gigasecond_days = (((10**9)/60)/60)/24
    # add the days of a gigasecond onto the date submitted
    gigasecond_from = date + gigasecond_days
    # return the new date
    gigasecond_from
  end
end
