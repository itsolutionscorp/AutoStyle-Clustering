class Gigasecond
  def self.from(date)
    seconds = 1_000_000_000
    (date.to_time + seconds).to_date
  end
end
