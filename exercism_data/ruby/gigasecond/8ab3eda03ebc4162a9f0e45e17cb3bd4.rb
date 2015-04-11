class Gigasecond

  attr_reader :date

  def initialize(date)
    @date = gigasecond_birthday(date)
  end

  def gigasecond_birthday(date)
    time = Time.new(date.year, date.month, date.day)
    time = time + (10**9)

    Date.new(time.year, time.month, time.day)
  end

end
