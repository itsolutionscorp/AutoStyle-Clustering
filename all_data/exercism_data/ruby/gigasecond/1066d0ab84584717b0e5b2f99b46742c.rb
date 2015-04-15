class Gigasecond
  def self.from(date_or_time)
    (date_or_time.to_time + 10**9).to_date
  end
end
