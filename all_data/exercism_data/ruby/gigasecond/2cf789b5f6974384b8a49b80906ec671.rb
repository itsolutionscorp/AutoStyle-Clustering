module Gigasecond
  def self.from(date_or_time)
    epoch = date_or_time.to_time.to_i
    Time.at(epoch + 10**9).to_date
  end
end
