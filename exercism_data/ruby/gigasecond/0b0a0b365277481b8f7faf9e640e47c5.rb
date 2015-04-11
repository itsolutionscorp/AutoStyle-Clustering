class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    gigasecond = 10**9
    minutes_in_gigasecond = gigasecond / 60
    hours_in_gigasecond = minutes_in_gigasecond / 60
    days_in_gigasecond = hours_in_gigasecond / 24

    @date + days_in_gigasecond
  end
end
