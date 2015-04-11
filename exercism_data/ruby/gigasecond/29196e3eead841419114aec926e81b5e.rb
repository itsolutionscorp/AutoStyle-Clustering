class Gigasecond
  GIGASECOND = 10**9

  def self.from(from_date)
    (from_date.to_time + GIGASECOND).to_date
  end
end
