class Gigasecond
  def self.from(day)
    Time.at(day.to_time.to_i + 10**9).to_date
  end
end
