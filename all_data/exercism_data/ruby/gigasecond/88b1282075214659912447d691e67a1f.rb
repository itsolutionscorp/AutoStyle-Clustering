require 'date'
require 'time'

class Gigasecond
  
  def self.from(date)

    gigasec = 10**9
    return Date.strptime((date.to_time.to_i + gigasec).to_s, '%s')

  end

end
