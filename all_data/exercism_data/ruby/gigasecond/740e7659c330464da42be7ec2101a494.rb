class Gigasecond

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + gigaseconds_in_days
  end

  def gigaseconds_in_days
    1000000000 / (60 * 60 * 24)
  end
end
