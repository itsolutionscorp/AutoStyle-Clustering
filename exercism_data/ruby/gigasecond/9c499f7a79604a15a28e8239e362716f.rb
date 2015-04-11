require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    epoch_seconds = date.to_time.tv_sec

    Time.at(epoch_seconds + 1_000_000_000).to_datetime.to_date
  end
end
