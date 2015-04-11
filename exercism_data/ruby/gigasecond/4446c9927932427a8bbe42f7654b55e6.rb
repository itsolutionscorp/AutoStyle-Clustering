class Gigasecond
  GIGASECONDS = ((10**9) / 24 / 60 / 60)

  def self.from(date)
    date + GIGASECONDS
  end
end
