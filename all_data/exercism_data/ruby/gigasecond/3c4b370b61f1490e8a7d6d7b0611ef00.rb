require 'date'
class Gigasecond
  def self.from(date)
    seconds_in_gigasecond = 10**9
    seconds_in_day = 60*60*24
    days_in_gigasecond = seconds_in_gigasecond/seconds_in_day
    giga_birthday = date + days_in_gigasecond
  end
end
