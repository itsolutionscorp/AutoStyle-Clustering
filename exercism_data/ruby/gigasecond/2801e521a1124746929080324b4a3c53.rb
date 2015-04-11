require 'date'
require 'time'

class Gigasecond
  
  GIGA = 10**9 
  EPOCH = Date.new.to_time.to_i

  def self.from(date) 
    anniversary = date.to_time.to_i + GIGA  
    Time.at(anniversary).to_date
  end


end
