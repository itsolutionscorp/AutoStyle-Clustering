require 'date'
class Gigasecond
  def self.from(from_date)
    #
    # Take the Julian date number from the from_date, add 1 billion seconds (converted into days)
    # to it. Make a new Date object from it using the Date.jd instantiator and return it.
    #
    Date.jd(from_date.jd+((((10**9)/60)/60)/24))
  end
end
