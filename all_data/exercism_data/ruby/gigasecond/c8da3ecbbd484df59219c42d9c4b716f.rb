class Gigasecond

  BILLION = 1000000000
  SECONDS_IN_A_DAY = ((60*60)*24)
  DAYS_IN_BILLION_SECONDS = BILLION / SECONDS_IN_A_DAY

  def self.from(date)
    date + DAYS_IN_BILLION_SECONDS
  end

end
