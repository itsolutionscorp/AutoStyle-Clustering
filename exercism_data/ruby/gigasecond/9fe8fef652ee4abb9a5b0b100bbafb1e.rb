require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    time_obj = date.to_time
    gs_later = time_obj + (10**9)
    gs_later.to_date
  end

  

end
