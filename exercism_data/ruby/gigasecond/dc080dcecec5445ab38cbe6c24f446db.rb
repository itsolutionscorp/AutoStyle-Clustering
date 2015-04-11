class Gigasecond
  GIGASECOND = 1_000_000_000

  def self.from(start_date)
    Time.at(start_date.to_time.to_i + GIGASECOND).to_date
  end
end
