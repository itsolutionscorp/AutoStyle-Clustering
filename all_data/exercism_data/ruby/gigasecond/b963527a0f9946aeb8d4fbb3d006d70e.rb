class Gigasecond
  def self.from(date)
    seconds = date.to_time.to_i
    gigasecond = seconds + (10**9)
    Date.parse(Time.at(gigasecond).to_s)
  end
end
