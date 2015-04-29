module Gigasecond
  def self.from(start)
    Time.at(start.to_time + 1000000000).to_date
  end
end
