require 'active_support/all'

class Gigasecond
  def self.from(date)
    seconds_in_gigasecond = 10**9

    current_time = date.to_time
    current_time += seconds_in_gigasecond.seconds
    future_date = current_time.to_date
  end
end
