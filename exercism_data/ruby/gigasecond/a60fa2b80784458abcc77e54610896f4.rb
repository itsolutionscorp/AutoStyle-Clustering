class Gigasecond
  def self.from(date)
    day = 60 * 60 * 24
    date + (1000000000/day)
  end
end

