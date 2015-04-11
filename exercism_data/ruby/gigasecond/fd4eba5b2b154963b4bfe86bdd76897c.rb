class Gigasecond
  GIGASECOND_INTERVAL = 10**9

  def self.from(date)
    (date.to_time + GIGASECOND_INTERVAL).to_date
  end
end
