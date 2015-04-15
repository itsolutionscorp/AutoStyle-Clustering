class Gigasecond
  def self.from(date)
    (date.to_time + 1000000000).to_date
  end
end
