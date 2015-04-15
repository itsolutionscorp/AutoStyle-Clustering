class Gigasecond
  def self.from(date)
    date + 1_000_000_000 / (60*60*24)
  end
end
