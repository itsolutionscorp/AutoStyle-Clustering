class Gigasecond
  def initialize(date)
    @date = date
    @days_in_gigasecond = 1000000000 / (60 * 60 * 24)
  end

  def date
    @date + @days_in_gigasecond
  end
end
