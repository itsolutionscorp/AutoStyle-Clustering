require 'date'

class Gigasecond
  A_GIGASECOND = 1e9

  def self.from(dob)
    (dob.to_time + A_GIGASECOND).to_date
  end
end
