require 'date'
require 'time'

class Gigasecond
  def self.value
    (10**9) / (24.0 * 60 * 60)
  end

  def self.from(date)
    raise TypeError('must be a date or time') unless [Date, Time].include? date.class
    Date.parse((date.to_date + Gigasecond.value).strftime(format='%F'))
  end
end
