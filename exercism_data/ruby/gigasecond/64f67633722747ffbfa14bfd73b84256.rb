class Gigasecond
  attr_reader :start_date
  GIGASECOND = 10**9

  def initialize(date)
    @start_date = date
  end

  def date
    start_date + gigasecond_as_days
  end

  def gigasecond_as_days
    GIGASECOND / 86400
  end
end
