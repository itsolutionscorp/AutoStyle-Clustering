class Gigasecond
  def initialize(birthday)
    @birthday = birthday
  end

  def date
    @birthday + gigasecond_in_days
  end

  private

  def gigasecond_in_days
    1_000_000_000 / 60 / 60 / 24
  end
end
