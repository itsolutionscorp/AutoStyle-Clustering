require 'date'

class Gigasecond

  def initialize(startdate)
    @starting_time = startdate.to_time
  end

  def date
    (@starting_time + 10**9).to_date
  end

end
