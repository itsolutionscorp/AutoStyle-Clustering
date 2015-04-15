require 'date'

class Gigasecond < Date 
  # returns date one Gigasecond (10**9 seconds) after given date
  def self.from( from_date )
    gigasecond_in_seconds = 10**9;
    return (from_date.to_time + gigasecond_in_seconds).to_date
  end
end
