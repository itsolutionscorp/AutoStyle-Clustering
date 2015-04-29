require 'date'

class Gigasecond
  def self.from(date)
    time = date.to_time
    gigatime = time + 10**9
    gigatime.to_date
  end
end
