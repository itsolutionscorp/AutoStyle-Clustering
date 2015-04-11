class Gigasecond
  def self.from(date)
    Time.at(date.to_time.to_i + 1000000000).to_date
  end
end
