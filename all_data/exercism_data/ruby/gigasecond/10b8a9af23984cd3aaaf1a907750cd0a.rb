class Gigasecond
  def self.from(a)
    (a.to_time + 1e9).to_date
  end
end
