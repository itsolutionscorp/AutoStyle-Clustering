require 'date'
require 'time'

class Gigasecond
  def self.from(birthdate)
    birthseconds = birthdate.to_i
    gs = birthseconds + (10**9)
    Time.at(gs).utc
  end
end
