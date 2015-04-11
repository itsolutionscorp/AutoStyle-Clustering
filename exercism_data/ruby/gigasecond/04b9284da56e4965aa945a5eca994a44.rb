require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    t = Time.mktime(date.year, date.month, date.day)
    gs = t + 1000000000
    Date.new(gs.year, gs.month, gs.day)
  end

end
