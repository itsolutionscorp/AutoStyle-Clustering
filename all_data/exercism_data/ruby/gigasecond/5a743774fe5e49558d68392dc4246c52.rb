class Gigasecond
  GIGASEC = (10**9)
  def self.from(date)
    Time.at(date.to_time.to_i + GIGASEC).to_date
  end
end
