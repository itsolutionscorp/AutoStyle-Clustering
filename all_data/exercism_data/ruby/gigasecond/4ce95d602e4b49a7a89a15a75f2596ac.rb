class Gigasecond
  def self.from(date)
    (date.to_time + 1e9).to_date
  end
end
