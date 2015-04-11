require 'date'
require 'time'

class Gigasecond
  def self.in_seconds
    (10**9)
  end

  def self.from(date)
    raise TypeError('must be a date or time') unless [Date, Time].include? date.class
    (Time.at(date.to_time.to_i + Gigasecond.in_seconds)).to_date
  end
end
