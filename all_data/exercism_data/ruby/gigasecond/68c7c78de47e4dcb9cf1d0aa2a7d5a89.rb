class Gigasecond

  BILLION_SECS = 10**9

  def initialize(birthday)
    @birthday = birthday.to_time + BILLION_SECS
  end

  def date
    @birthday.to_date
  end

end
