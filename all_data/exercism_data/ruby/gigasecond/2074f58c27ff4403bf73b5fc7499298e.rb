require 'date'

class Gigasecond
  def self.from(date)
    days_to_go = 10**9 / (60*60*24)
    date + days_to_go
  end
end
