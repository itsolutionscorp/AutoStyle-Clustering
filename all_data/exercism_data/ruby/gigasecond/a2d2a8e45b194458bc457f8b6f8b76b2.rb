class Gigasecond
  def self.from(date)
    date + days_in_a_gs
  end

  def self.days_in_a_gs
    1_000_000_000 / (60 * 60 * 24)
  end
end
