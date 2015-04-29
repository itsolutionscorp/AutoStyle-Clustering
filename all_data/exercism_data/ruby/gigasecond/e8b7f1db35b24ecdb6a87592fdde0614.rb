class Gigasecond
  ONE_BILLION = 1_000_000_000

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    day_of_gigaversary
  end

  private

  def time_of_birthday
    @birthday.to_time
  end

  def gigaversary
    time_of_birthday + ONE_BILLION
  end

  def day_of_gigaversary
    Date.new(gigaversary.year, gigaversary.month, gigaversary.day)
  end
end
