require 'date'
require 'time'

class Gigasecond

  GIGASEC = 10**9
  
  def self.from(date)

    return date + GIGASEC/86400

  end

end
