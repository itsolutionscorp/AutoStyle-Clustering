class Gigasecond
  def self.from(date)
    giga_seconds = 10.0**9
    giga_days    = (giga_seconds / (24.0*60.0*60.0)).round
    date + giga_days
  end
end
