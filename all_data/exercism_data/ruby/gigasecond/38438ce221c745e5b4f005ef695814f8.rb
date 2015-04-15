class Gigasecond
  GIGASEC_DAYS = 1000000000/60/60/24

  def self.from(date)
    date + GIGASEC_DAYS
  end
end
