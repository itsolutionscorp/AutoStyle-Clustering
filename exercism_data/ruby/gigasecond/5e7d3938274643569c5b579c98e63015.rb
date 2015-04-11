class Gigasecond
  GIGASECOND = 10**9

  def self.from(date)
    Time.at(date.to_time.to_i + GIGASECOND).to_date
  end
end
