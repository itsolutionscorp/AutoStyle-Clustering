class Gigasecond

  BILLION_SECS = 10**9

  def initialize(birthday)
    @gigasecond = birthday.to_time + BILLION_SECS
  end

  def date
    @gigasecond.to_date
  end

end
