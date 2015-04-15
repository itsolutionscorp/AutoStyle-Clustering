module Gigasecond
  ELAPSED_TIME = 10**9

  def self.from(date)
    Time.at(date.to_time.to_i + ELAPSED_TIME).to_date
  end
end
