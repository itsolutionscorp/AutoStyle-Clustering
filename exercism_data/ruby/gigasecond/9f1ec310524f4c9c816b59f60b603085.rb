require 'date'

class Gigasecond < Date 
  def self.from( date )
    (date.to_time + (10**9)).to_date
  end
end
