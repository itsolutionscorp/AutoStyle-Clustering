class Gigasecond
  def self.from(from_date)
    giga_second_time = Time.parse(from_date.to_s) + 10**9
    Date.parse(giga_second_time.to_s)
  end
end
