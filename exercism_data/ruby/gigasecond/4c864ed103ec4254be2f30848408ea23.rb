require 'date'
require 'time'

class Gigasecond

  def self.a_billion_seconds
    1000000000
  end# of a_billion_seconds


  def self.from(birthday)
    # Takes a Date object
    # Returns a Date object 1*10^9 seconds older
    #   Note: It seems hacky to turn the dates into strings and back again.
    #   How can I avoid that? 
    seconds_since_epoch = birthday.strftime('%s').to_i
    a_billion_seconds_later = seconds_since_epoch + a_billion_seconds
    Date.strptime(a_billion_seconds_later.to_s, '%s')
  end# of self.from
  
end# of Gigasecond
