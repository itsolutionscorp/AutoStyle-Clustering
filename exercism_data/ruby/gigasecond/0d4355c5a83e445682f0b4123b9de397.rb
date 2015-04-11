require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    t = Time.mktime(date.year, date.month, date.day)
    gs = t + 1000_000_000
    Date.new(gs.year, gs.month, gs.day)
  end

end
