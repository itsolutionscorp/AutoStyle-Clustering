require 'date'

class Gigasecond
  GS = 10**9

  def self.from(date)
    (date.to_time + GS).to_date
  end
end
