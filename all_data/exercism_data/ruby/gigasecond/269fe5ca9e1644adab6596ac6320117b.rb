class Gigasecond
  def self.from(date)
    seconds = date.to_time.to_i + (10**9)
    Time.at(seconds).to_date
  end
end
