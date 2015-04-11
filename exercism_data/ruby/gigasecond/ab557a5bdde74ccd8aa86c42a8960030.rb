class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    date_of_gigasecond
  end

  private

  def date_of_gigasecond
    seconds = date_to_seconds(@date) + 1_000_000_000
    seconds_to_date(seconds)
  end

  def seconds_to_date(seconds)
    Date.strptime(seconds.to_s, '%s')
  end

  def date_to_seconds(date)
    date.to_time.to_i
  end
end
