class Gigasecond
  GIGASECONDS_PER_DAY = 10.0**9 / (24 * 60 * 60)

  def self.from( date )
    future_date = date + GIGASECONDS_PER_DAY

    Date.new(future_date.year, future_date.month, future_date.day)
  end
end
