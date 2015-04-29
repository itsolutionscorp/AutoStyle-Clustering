class Gigasecond

  def initialize(date)
    @birth = date.to_date
  end

  def date
    anniversary = @birth.to_time + gigasecond
    anniversary.to_date
  end

  private

  def gigasecond
    10**9
  end
end
