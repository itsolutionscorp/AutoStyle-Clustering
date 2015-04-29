module Gigasecond
  def self.from(date_or_time)
    epoch = date_or_time.to_time.to_i
    time = Time.at(epoch + 10**9)
    Date.new(time.year, time.month, time.day)
  end
end
