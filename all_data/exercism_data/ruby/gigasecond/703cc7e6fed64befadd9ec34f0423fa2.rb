require 'date'

class Gigasecond
  def self.from(birthdate)
    (birthdate.to_time + 1000 * 1000 * 1000).to_date
  end
end
