class Gigasecond
  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    seconds = to_seconds(@birthdate) + (10**9)
    Time.at(seconds).to_date
  end

  def to_seconds(datetime)
    datetime.to_time.to_i
  end
end
