class Gigasecond
  
  ONE_G = 10**9
  
  def initialize(any_date)
    @any_date = any_date
  end  
      
  def date
    secs_in_any_date = @any_date.to_time.to_i
    gigasec_anniversary =  secs_in_any_date + ONE_G
    Time.at(gigasec_anniversary).to_date
  end  
  
end  
