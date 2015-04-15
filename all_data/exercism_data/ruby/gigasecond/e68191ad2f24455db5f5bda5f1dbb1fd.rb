require 'date'
require 'time'

class Gigasecond

  def self.from(birthdate)

    # Convert birthdate to time
    bday_time = birthdate.to_time
    # Add 1 billion seconds
    gigasecond_anniversary_time = bday_time + 10**9
    # Return value as a date
    return gigasecond_anniversary_time.to_date

  end
end
