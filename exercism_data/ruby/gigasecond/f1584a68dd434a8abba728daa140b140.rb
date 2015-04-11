class Gigasecond
  def self.from(d)
    Time.at(d.to_time.to_i + (10**9)).to_date
  end
end
