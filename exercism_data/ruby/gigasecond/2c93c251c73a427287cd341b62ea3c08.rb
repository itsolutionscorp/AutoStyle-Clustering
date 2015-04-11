class Gigasecond

  def initialize b
    @date = (date_to_time(b) + 10**9).to_date
  end

  def date
    @date
  end

  private
  def date_to_time(date)
    y = date.year
    m = date.month
    d = date.day
    Time.new(y, m, d)
  end

  def days_in_seconds(days)
    days * 24 * 60 * 60
  end

end
