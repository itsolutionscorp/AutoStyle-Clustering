require 'date'
require 'time'

class Gigasecond
  def self.from(birthdate)
    birthdate + 10**9 
  end
end
