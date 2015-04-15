class Gigasecond
  Gigasecond_In_Days = 10**9 / (60 * 60 * 24)

  def self.from(date)
    date + Gigasecond_In_Days
  end
end
