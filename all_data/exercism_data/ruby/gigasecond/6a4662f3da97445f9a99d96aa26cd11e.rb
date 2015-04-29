class Gigasecond
  def self.gigasecond
    1_000_000_000
  end

  def self.from(date)
    Time.at(date.to_time.to_i + gigasecond).to_date
  end
end
