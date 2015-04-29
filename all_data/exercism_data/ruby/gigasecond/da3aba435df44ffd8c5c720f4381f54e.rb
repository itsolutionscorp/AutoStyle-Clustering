require 'date'
#
class Gigasecond
  @hours = @mins = 60
  @gigaseconds = 10**9

  def self.tz_offset_in_seconds
    zone = Time.now.strftime("%z").chars
    zone.shift(3).join.to_i * @hours * @mins +
      zone.join.to_i * @mins
  end

  def self.from(date)
    date_in_seconds = date.strftime("%s").to_i
    Date.parse(Time.at(date_in_seconds +
                       @gigaseconds -
                       tz_offset_in_seconds).to_s)
  end
end
