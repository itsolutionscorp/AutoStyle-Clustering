class Gigasecond
  def self.from(dt)
    Time.at(dt.to_time.to_i + 10**9).to_date
  end
end
