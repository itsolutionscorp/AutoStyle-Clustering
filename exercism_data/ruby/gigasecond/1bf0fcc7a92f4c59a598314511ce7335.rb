class Gigasecond
  SECONDS_PER_GIGASECOND = (10 ** 9)

  def self.from(date)
    gigasecond_anniversary = self.date_in_seconds(date) + SECONDS_PER_GIGASECOND

    self.convert_to_date(gigasecond_anniversary)
  end

  private

  def self.date_in_seconds(date)
    date.to_time.to_i
  end

  def self.convert_to_date(time)
    Time.at(time).to_date
  end
end
