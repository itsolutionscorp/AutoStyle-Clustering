require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    Date.parse Time.at(date.to_time.to_i + 10**9).to_s
  end

end
