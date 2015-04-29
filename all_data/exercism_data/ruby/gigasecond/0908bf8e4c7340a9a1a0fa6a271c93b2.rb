class Gigasecond
  require 'date'

  SECONDS_PER_GIGASECOND = 10**9

  def self.from(date_time)
    (date_time.to_time + SECONDS_PER_GIGASECOND).to_date
  end
end
