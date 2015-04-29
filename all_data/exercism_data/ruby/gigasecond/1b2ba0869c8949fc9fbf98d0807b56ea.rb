class Gigasecond
  GIGASEC = 1_000_000_000

  def self.from(date)
    (date.to_time + GIGASEC).to_date
  end
end
