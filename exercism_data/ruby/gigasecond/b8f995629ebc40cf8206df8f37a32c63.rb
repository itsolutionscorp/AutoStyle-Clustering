class Gigasecond

  # Single Responsibility: add 10^9 seconds to a given date(time)

  def self.from(date)
    (Time.new(date.year, date.month, date.day, 0,0,0) + 10**9).to_date
  end

end
