require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    second = date.to_time.to_i
    gs = second + 1_000_000_000
    Date.strptime(gs.to_s, '%s') + 1
  end
end
