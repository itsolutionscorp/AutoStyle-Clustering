module Gigasecond
  UNIT = (10**9) 
  def self.from(date)
    (date.to_time + UNIT).to_date
  end
end
