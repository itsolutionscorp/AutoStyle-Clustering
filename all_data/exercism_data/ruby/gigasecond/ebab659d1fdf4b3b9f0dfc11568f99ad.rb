class Gigasecond

  attr_reader :birthday
  SECONDS_PER_DAY = 60 * 60 * 24
  GIGASECOND = 10**9

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    birthday + gseconds_per_day
  end

  private

  def gseconds_per_day
    GIGASECOND / SECONDS_PER_DAY
  end

end
