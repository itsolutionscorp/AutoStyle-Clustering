# gigasecond.rb
# Calculates when a person will have their 1 gigasecond anniversary.
require 'date'
require 'time'

class Gigasecond
  def self.from(birthdate)
    birthdate = Date.parse(birthdate.utc.to_s) if birthdate.instance_of?(Time)
    gigasecond_anniversary = birthdate + Rational(10.0**9,60*60*24)
    gigasecond_anniversary = gigasecond_anniversary - gigasecond_anniversary.day_fraction
  end
end
