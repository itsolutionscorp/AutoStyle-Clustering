class Gigasecond
  SECONDS_IN_DAY = 86400

  def initialize(date)
    @date = date
  end

  def date
    @date + gigaseconds
  end

  private

  def gigaseconds
    10**9 / SECONDS_IN_DAY
  end
end
