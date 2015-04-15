require 'date'
require 'time'

class Gigasecond
  attr_reader :startdate
  def initialize(startdate)
    @startdate = startdate
  end
  
  def date
    (@startdate.to_time + 10**9).to_date
  end
end
