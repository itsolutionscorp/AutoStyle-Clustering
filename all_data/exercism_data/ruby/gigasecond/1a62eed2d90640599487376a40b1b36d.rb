class Gigasecond
  def self.from(date)
    anniversary = (date.to_time + 1000000000).to_date
  end
end
