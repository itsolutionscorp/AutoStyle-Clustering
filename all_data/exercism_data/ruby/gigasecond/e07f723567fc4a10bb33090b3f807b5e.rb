require 'date'
require 'time'

class Gigasecond

  def self.from(birthDate)
    (birthDate.to_time + 10**9).to_date
  end
end
