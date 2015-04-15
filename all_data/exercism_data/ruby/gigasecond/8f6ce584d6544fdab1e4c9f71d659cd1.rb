require 'pry'

class Gigasecond
  def self.from(date)
    gigasecond = 1000000000
    if date.class == Time
      seconds = (Time.now - date).to_i
      days = seconds / (24 * 3600)
      time_ago_in_days = (seconds - gigasecond) / (3600 * 24)
      date = Date.today - time_ago_in_days
    elsif date.class == Date
      time_ago_in_days = (Date.today - date).to_i
      time_ago_in_seconds = time_ago_in_days * 24 * 3600
      date = Date.today + ((gigasecond - time_ago_in_seconds) / (24 * 3600))
      date
    end
  end
end
