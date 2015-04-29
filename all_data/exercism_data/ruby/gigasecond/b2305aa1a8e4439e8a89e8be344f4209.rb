require 'date'
require 'time'
class Gigasecond
  def self.from(dt)
    Date.parse((Time.now + (1000000000-((Date.today-dt).to_i*86400))).strftime("%Y-%m-%d"))
  end
end
