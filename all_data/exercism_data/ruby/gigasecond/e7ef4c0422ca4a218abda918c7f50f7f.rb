require 'date'
require 'time'

class Gigasecond

  NUMBER_OF_DAYS_IN_GIGASECOND = 10**9 / (24*3600)
  def self.from(birthday)
    birthday + NUMBER_OF_DAYS_IN_GIGASECOND  
  end

end
