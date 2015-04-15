class Gigasecond

  def self.from(start_date)
    start_date +  1000_000_000 / (24 * 60 * 60)
  end

end
