require 'date'

class Gigasecond
  def self.from(date)
    t = Time.parse(date.to_s)
    Date.parse( (t + 10**9).to_s )
  end
end
