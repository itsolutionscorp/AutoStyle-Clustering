class Gigasecond
  def self.from(date)
    seconds = date.to_time.to_i + (10**9)
    Date.parse(Time.at(seconds).to_s)
  end
end
