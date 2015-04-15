class Gigasecond
  def self.from(datetime)
    datetime + (10**9 / 86400)
  end
end
