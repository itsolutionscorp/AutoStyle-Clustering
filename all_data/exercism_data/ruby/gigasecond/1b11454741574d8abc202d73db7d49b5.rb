class Gigasecond
  # Number of days in a gigasecond (gigasecs => secs => mins => days)
  GIGASEC_DAYS = 1000000000/60/60/24

  def self.from(date)
    date + GIGASEC_DAYS
  end
end
