class Gigasecond

  SECONDS_PER_GIGASECOND = 1e9.to_i
  SECONDS_PER_DAY = 60*60*24
  DAYS = SECONDS_PER_GIGASECOND/SECONDS_PER_DAY

  def self.from(start_date)
    start_date + DAYS
  end

end

      
       
 
  
