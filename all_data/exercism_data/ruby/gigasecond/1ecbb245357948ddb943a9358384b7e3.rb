require 'date'

class Gigasecond
  def self.from(birthDay)
    return (birthDay.to_time + (10**9)).to_date
  end
end
