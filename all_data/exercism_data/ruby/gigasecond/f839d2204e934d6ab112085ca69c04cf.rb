class Gigasecond
  def initialize(date)
    @gs_date = date + (10**9 / 86400) #divide by # of seconds in a day
  end
  def date
    @gs_date
  end
end
