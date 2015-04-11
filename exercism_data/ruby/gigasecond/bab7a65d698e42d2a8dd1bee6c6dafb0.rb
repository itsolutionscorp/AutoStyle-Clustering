require "date"
# require_relative "time_utilities"

class Gigasecond

  def self.from(input)
    if input.is_a? Date
      input.add_gigasecond
    elsif input.is_a? Time
      input.convert_to_date.add_gigasecond
    end
  end

end

class Date
  def add_gigasecond
    add_days(10**9)
  end

  def add_days(s)
    seconds_per_day = 60*60*24
    days = s/seconds_per_day
    self + Rational(days)
  end
end

class Time
  def convert_to_date
    Date.parse(self.strftime('%Y/%m/%d'))
  end
end
