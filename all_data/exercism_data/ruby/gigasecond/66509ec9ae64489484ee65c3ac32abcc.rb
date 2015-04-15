module Gigasecond

  HOURS = 24
  MINUTES = 60
  SECONDS = 60
  DAYS_PER_GIGASECOND = 10**9/HOURS/MINUTES/SECONDS

  def self.from(date)
    date + DAYS_PER_GIGASECOND
  end

end
