require 'date'
require 'time'

class Gigasecond

  GIGASEC = 10**9
  
  def self.from(date)

    return Date.strptime((date.to_time.to_i + GIGASEC).to_s, '%s')

  end

end
