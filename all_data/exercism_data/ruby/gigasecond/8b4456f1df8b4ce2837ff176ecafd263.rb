module Gigasecond
  GIGASECOND = 10**9

  def self.from(time)
    (time.to_time + GIGASECOND).to_date
  end
end
