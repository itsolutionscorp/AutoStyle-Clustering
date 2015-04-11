require 'date'
require 'time'

class Gigasecond

  def self.from(birthdate)
    Time.at(birthdate.to_time.utc.to_i + 1_000_000_000).to_date
  end

end
