require 'date'

class Gigasecond
  def self.from(date_time)
    giga_second = 1_000_000_000
    future_date = nil

    if date_time.is_a? Date
      num_days =  giga_second / (60 * 60 * 24)
      future_date = date_time + num_days
    else
      future_time = date_time + giga_second
      future_date = future_time.to_date
    end
    future_date
  end
end
