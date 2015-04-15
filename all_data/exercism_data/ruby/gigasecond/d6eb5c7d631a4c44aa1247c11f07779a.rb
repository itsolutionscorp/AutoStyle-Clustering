class Gigasecond
  def self.from(start)
    interval = 10**9
    interval /= 86400 if start.is_a?(Date)
    finish = start + interval
    finish.to_date
  end
end
