class Gigasecond

  def self.from(date)
    Date.parse (date.to_time + (10**9)).to_s
  end

end
