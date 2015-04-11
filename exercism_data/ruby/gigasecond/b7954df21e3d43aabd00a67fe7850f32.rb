require 'date'
require 'time'

class Gigasecond
  TEN_BILLION = 1000000000

  def self.from(dob)
    (dob.to_time + TEN_BILLION).to_date # need a time object to add seconds
  end
end
