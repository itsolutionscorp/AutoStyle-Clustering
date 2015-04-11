class Gigasecond
  SECONDS_PER_GIGASECOND = 1_000_000_000
  SECONDS_PER_DAY        = 86_400

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + (SECONDS_PER_GIGASECOND / SECONDS_PER_DAY)
  end
end
