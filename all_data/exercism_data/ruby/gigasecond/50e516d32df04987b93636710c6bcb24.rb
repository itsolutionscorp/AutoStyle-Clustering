class Gigasecond
  SECONDS = 10**9 / 86400

  def self.from(date)
    date + SECONDS
  end
end