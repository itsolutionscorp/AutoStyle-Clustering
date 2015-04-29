module Gigasecond
  GIGA = 10**9

  def self.from date
    future = date.to_time + GIGA

    future.to_date
  end
end
