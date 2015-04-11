class Gigasecond

  # Single Responsibility: add 10^9 seconds to a given date(time)

  def self.from(date)
    time = date_to_time(date)
    time = add_gigasecond(time)
    return time.to_date
  end

  private

  def self.date_to_time(date)
    Time.new(date.year, date.month, date.day, 0,0,0)
  end

  def self.add_gigasecond(time)
    time + 10**9
  end

end
