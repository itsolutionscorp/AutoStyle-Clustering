module Gigasecond
  def self.from(date)
    seconds = date.to_time.to_i
    gigaseconds = seconds + 1_000_000_000
    Time.at(gigaseconds).to_date
  end
end
