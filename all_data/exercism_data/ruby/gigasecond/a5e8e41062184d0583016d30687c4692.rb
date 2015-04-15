class Gigasecond

  def self.from(day)
    (day.to_time + (10**9)).to_date
  end

end
