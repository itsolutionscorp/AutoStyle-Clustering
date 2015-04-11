require 'date'
require 'time'

class Gigasecond < Date


  def self.from(date)

    gig = date.to_time.to_i + (10**9)

     t = Time.at(gig)

     Date.parse(t.to_s)

  end



end


  
