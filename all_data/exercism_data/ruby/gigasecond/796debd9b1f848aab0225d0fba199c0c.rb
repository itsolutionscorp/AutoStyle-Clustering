class Gigasecond

  attr_reader :date

  def initialize(date)
    @date = gigasecond_birthday(date)
  end

  def gigasecond_birthday(date)
    time = date.to_time + (10**9)
    time.to_date
  end

end
