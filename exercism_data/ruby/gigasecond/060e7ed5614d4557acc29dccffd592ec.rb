require 'date'
require 'time'

class Gigasecond

  def self.a_billion_seconds
    seconds = 1000000000
    minutes = seconds/60
    hours = minutes/60
    days = hours/24
  end# of a_billion_seconds


  def self.from(birthday)

    a_billion_seconds_later = birthday + a_billion_seconds

  end# of self.from
  
end# of Gigasecond
