class Gigasecond
  GIGASECOND = (10 ** 9)

  def self.from(date)
    gigasecond_anniversary = (date.to_time + GIGASECOND).to_date
  end
end
