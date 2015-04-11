class Gigasecond

  def self.from(date)
    date + (10**9 / 60 / 60 / 24)
  end

end
