class Gigasecond
  @gigasecond = 10**9
  def self.from(date)
    (date.to_time + @gigasecond).to_date
  end
end
