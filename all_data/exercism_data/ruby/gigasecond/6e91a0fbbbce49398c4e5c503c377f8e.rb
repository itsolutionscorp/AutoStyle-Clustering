class Gigasecond
  
  GIGASECONDS = 1000000000
  
  def self.from(date)
    
    seconds = date.strftime('%s').to_i
    future = seconds + GIGASECONDS
    
    Time.at(future).to_date
           
  end
  
end
