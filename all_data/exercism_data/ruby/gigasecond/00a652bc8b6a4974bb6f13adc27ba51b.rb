class Gigasecond
  def self.from(dt)
    (dt.to_time + 10**9).to_date
  end
end
