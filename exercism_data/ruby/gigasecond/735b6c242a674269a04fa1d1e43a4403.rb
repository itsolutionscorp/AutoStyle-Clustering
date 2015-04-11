class Gigasecond
  GIGASECOND = 10.0**9
  DAY = 24 * 60 * 60

  def self.from( date )
    date += GIGASECOND / DAY

    Date.new(date.year, date.month, date.day)
  end
end
