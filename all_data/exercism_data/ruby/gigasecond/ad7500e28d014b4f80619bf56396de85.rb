class Gigasecond
  def self.from(date)
    seconds = 10**9
    (date.to_time + seconds).to_date
  end
end
