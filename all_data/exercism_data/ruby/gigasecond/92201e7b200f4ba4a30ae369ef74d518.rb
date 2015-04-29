class Gigasecond

  def initialize(birthdate)
    @birthdate = birthdate.to_time
  end

  def date
    (@birthdate + (10**9)).to_date
  end
end
