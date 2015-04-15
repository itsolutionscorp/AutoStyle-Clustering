module Gigasecond
  def self.from(date)
    date + DAYS
  end

  SECONDS = 10 ** 9
  MINUTES  = SECONDS / 60
  HOURS  = MINUTES / 60
  DAYS  = HOURS / 24
end
