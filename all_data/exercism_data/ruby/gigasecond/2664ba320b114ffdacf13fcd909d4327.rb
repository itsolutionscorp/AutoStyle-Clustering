class Gigasecond
  def self.from(d)
    return (d.to_time + 10**9).to_date
  end
end
