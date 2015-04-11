class Gigasecond
  GIGASECOND_AS_DAYS = 10**9 / (60 * 60 * 24)

  attr_reader :date

  def initialize date
    @date = date + GIGASECOND_AS_DAYS
  end
end
