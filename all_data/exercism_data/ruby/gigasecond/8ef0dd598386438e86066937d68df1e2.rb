class Gigasecond
  
  DAYS_IN_A_GIGASECOND = (10**9)/60/60/24
  
  def self.from (given_date)
    given_date + DAYS_IN_A_GIGASECOND
  end
end