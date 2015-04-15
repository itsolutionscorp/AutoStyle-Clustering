class Gigasecond
  def self.from(date)
    Time.at(date.to_time.to_f + 1_000_000_000).to_date
  end
end
