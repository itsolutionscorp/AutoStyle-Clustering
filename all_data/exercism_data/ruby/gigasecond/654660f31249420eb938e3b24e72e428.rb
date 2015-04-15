module Gigasecond
  def self.from(date)
    timestamp = date.to_time.to_i + 1_000_000_000
    Time.at(timestamp).to_date
  end
end
