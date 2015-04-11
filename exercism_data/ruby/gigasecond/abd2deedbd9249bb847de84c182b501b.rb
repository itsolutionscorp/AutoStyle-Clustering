class Gigasecond
  GIGASEC = 1000000000
  def self.from(date)
    Time.at(date.to_time.to_i + GIGASEC).to_date
  end
end
