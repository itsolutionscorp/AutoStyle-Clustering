class Gigasecond
  ONE_GIGASECOND_IN_DAYS = 1_000_000_000 / 86_400

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + ONE_GIGASECOND_IN_DAYS
  end
end
