require 'date'
require 'time'

class Gigasecond
  GIGASECOND_IN_SECONDS = 10 ** 9

  def self.from(date)
    given_date_in_seconds = date.to_time
    result_date_in_seconds = given_date_in_seconds + GIGASECOND_IN_SECONDS
    result_date_in_seconds.to_date
  end

end
