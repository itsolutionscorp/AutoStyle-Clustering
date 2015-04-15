class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    new_date = date_in_seconds + gigasecond
    Date.parse(time_to_date(new_date))
  end

  private

  def date_in_seconds
    @date.to_time.to_i
  end

  def time_to_date(time)
    Time.at(time).strftime('%Y/%m/%d')
  end

  def gigasecond
    10**9
  end
end
