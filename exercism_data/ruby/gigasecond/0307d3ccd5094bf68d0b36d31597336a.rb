class Gigasecond
  def self.from(date)
    return Date.parse(date.to_s) + (10**9 / 60 / 60 / 24 + 1) if date.class == Time

    date + (10**9 / 60 / 60 / 24)
  end
end
