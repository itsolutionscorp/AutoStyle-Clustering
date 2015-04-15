class Gigasecond

  GS = 10**9

  def self.from(original_date)
    return (original_date + GS).to_date unless original_date.class == Date

    days = self.to_days(self.to_hours(self.to_min))
    return (original_date + days).to_date
  end

  def self.to_min
    GS / 60
  end

  def self.to_hours(min)
    min / 60
  end

  def self.to_days(hours)
    hours / 24
  end

end
