class Gigasecond
  GIGASEC = 1_000_000_000

  def self.from(date)
    Time.at(date.to_time.to_i + GIGASEC).to_date
  end
end
