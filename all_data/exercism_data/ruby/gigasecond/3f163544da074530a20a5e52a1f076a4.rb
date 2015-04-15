class Gigasecond
  def initialize(date)
    @birthday = date.to_time
  end

  def date
    anniversary.to_date
  end

  def anniversary
    @birthday + 10**9
  end
end
