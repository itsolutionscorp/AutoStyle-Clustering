class Gigasecond

  def self.from(start)
    (start.to_time + 1000000000).to_date
  end

end
