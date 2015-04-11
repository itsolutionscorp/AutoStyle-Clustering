class Gigasecond
  def self.from(from_date)
    (from_date.to_time + 10**9).to_date
  end
end
