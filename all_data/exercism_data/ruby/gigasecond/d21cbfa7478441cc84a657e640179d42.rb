require 'date'

class Gigasecond

  def self.from(birthdate)
    birthdate_in_seconds = birthdate.to_time.to_i
    gigasecond_anniversary_in_seconds = birthdate_in_seconds + 10**9

    Time.at(gigasecond_anniversary_in_seconds).to_date
  end

end
