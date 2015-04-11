class Gigasecond
  def self.from date
    gigasecond = 10**9
    giga_date = (date.to_time + gigasecond).to_date
  end
end
