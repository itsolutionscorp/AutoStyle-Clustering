require 'date'

class Gigasecond
  def self.from date
    Date.strptime((date + (10**9)/86400.0).to_s.slice(0, 10))
  end
end
