require 'date'
require 'time'

class Gigasecond
  ONE_BILLION_SECONDS = 10**9
  
  attr_reader :start_date

  def initialize(start_date)
    @start_date = start_date
  end

  def date
    (start_date.to_time + ONE_BILLION_SECONDS).to_date
  end
end
