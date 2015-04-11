class Gigasecond
  GS = 1_000_000_000
  def self.from(start_date)
    Time.at(GS + start_date.to_time.to_i).to_date
  end
end
