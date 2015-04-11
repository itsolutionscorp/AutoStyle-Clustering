class Gigasecond
  SECONDS_IN_ONE_GIGASECOND = 10**9.freeze

  def self.from(origin_date)
    (origin_date.to_time + SECONDS_IN_ONE_GIGASECOND).to_date
  end
end
