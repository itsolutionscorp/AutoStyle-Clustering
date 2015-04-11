class Gigasecond
  GIGASECOND = 10**9
  GIGASECOND_IN_DAYS = GIGASECOND / 60 / 60 / 24

  class << self
    def from(date_or_time)
      date_or_time.to_time.utc.to_date + GIGASECOND_IN_DAYS
    end
  end
end
