require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    anniversary = date.to_time + 10**9
    anniversary.to_date
  end

end

#    date.kind_of?(Time) ? anniversary = date + 10**9 : anniversary = Time.local(date.year, date.month, date.day) + 10**9
#    Date.new(anniversary.year, anniversary.month, anniversary.day)
