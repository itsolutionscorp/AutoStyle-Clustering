require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    date.kind_of?(Time) ? anniversary = date + 10**9 : anniversary = Time.local(date.year, date.month, date.day) + 10**9
    Date.new(anniversary.year, anniversary.month, anniversary.day)
  end

end
