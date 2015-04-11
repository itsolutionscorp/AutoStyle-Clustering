class Gigasecond
  def self.from (in_date)
    (Time.at((in_date.to_time.to_i)+1_000_000_000)).to_date
  end
end
