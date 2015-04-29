require 'time'
require 'date'

class Gigasecond
  def self.from(from)
    d = DateTime.parse(from.to_s)
    t = Time.mktime(d.year, d.month, d.day,
                    d.hour, d.min, d.sec) + 1_000_000_000
    Date.parse(t.to_s)
  end
end
