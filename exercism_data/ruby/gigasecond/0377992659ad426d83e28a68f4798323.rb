require 'date'

class Gigasecond
  attr_reader :date
  
  def initialize(date)
    @date = add_gigasecond(date)
  end
  
  private
  def add_gigasecond(date)
    start_time = date.to_time
    gigasecond = 10**9
    (start_time + gigasecond).to_date
  end
end
