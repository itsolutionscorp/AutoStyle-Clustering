class Gigasecond
  def self.from(start_date)
    gigasecond_in_days = 10**9 / 60 / 60 / 24
    start_date + gigasecond_in_days
  end
end
