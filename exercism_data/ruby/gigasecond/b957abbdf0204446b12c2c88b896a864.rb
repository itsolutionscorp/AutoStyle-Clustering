require 'date'

class Gigasecond
  attr_accessor :start_date

  SECONDS_IN_GS = 10**9

  def initialize(start_date)
    @start_date = start_date
  end


  def date
    add_gigaseconds_to_start_time
  end

  def add_gigaseconds_to_start_time
    (@start_date.to_time + SECONDS_IN_GS).to_date
  end

end
