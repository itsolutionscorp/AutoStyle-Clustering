require 'date'
require 'time'

class Gigasecond
  def self.from(birthdate)
    gigasecond = 10**9
    bday_time = birthdate.to_time
    gigasecond_anniversary_time = bday_time + gigasecond
    return gigasecond_anniversary_time.to_date
  end
end
