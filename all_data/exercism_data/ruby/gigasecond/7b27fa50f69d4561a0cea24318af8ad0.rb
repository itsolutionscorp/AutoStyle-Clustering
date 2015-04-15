require 'pry'

class Gigasecond
  def self.from(date_or_time)
    gigasecond = 10**9
    seconds_since_epoch = date_or_time.to_time.tv_sec
    Time.at(seconds_since_epoch + gigasecond).to_date
  end
end
