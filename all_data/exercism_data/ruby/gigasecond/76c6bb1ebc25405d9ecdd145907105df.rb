module Gigasecond
  ELAPSED_TIME = 10**9

  def self.from(date)
    (date.to_time + ELAPSED_TIME).to_date
  end
end
