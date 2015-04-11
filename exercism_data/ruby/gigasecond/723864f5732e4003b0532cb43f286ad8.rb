module Gigasecond
  def self.from(date)
    Time.at(date.to_datetime.to_time.to_i + (10**9)).to_date + 1
  end
end
