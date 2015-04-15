class Gigasecond
  def initialize birthdate
    @birthdate = birthdate
  end

  def date
    (@birthdate.to_time + 10**9).to_date
  end
end
