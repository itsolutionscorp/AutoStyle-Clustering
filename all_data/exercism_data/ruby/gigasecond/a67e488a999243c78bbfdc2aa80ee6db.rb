class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    gigaseconds = 10**9
    seconds_in_a_day = 24*60*60
    days_for_gigaseconds = gigaseconds/seconds_in_a_day
    @date + days_for_gigaseconds
  end
end
