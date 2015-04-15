class Gigasecond

  GIGASECOND = 10**9 # A gigasecond (1000000000)

  def self.from(date)
    (date.to_time + GIGASECOND).to_date
  end
end
