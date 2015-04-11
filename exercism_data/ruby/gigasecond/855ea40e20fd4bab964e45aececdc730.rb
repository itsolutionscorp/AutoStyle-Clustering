require 'date'
require 'time'

class Gigasecond
  def self.from(d)
    Date.parse((Time.parse(d.to_s) + 10**9).to_s)
  end
end
