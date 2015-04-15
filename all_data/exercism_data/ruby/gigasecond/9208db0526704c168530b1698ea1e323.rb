module Gigasecond

  SECONDS = 1e9.to_i
  MINUTES = SECONDS/60
  HOURS = MINUTES/60
  DAYS = HOURS/24

  def self.from(start_date)
    start_date + DAYS
  end

end

      
       
 
  
