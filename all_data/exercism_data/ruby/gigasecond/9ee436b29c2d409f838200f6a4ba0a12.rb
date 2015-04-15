class Gigasecond
  def self.from(d)
    e = d.to_time + 1000000000
    e.to_date
  end
end
