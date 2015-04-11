class Gigasecond
  def self.from(date)
    start_year = date.year
    start_month = date.month
    start_day = date.day
    return (Time.local(start_year, start_month, start_day) + 10**9).to_date
  end
end
