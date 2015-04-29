module Gigasecond
  class << self
    GIGASECOND = 10**9

    def from(date)
      start_time = date.to_time
      anniversary_time = start_time + GIGASECOND
      anniversary_time.to_date
    end
  end
end
