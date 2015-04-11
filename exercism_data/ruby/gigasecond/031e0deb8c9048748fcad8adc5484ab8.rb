class Gigasecond
  def self.from(start)
    interval = 10**9
    interval /= 86400 if start.is_a?(Date)
    finish = start + interval
    Date.new(finish.year, finish.month, finish.day)
  end
end
