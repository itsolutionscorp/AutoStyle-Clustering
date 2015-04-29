require 'date'
class Time
  def to_date
    Date.parse(self.strftime("%Y%m%d"))
  end
end

class Gigasecond
  def self.from date_or_time
    case date_or_time
    when Date then date_or_time + gigasecond_to_days
    when Time then (date_or_time + gigasecond).to_date
    end
  end

  def self.gigasecond
    @@gigasecond ||= (10 ** 9)
  end

  def self.gigasecond_to_days
    @@gigasecond_day ||= gigasecond / 60 / 60 / 24
  end
end
