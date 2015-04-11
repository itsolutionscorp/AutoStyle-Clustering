class Gigasecond

  GS_SECONDS = 10**9

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    (@birthdate.to_time + GS_SECONDS).to_date
  end

end
