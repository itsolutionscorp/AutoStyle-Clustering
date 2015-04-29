module Gigasecond
  GIGASEC_DAYS = (10**9)/60/60/24
  
  def self.from(date)
    date + GIGASEC_DAYS
  end
end
