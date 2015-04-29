require 'date'
class Gigasecond
  
  def self.from(date)
  
    if date.is_a? Date
      date = Date._parse(date.to_s)      
    else
      date = {year:date.year,mon:date.month,mday:date.day,
              hour:date.hour,min:date.min,sec:date.sec}     
    end
  
    t = Time.new(date[:year],date[:mon],date[:mday],date[:hour],date[:min]).to_i + 10**9      
    t = Time.at(t)
  
    Date.new(t.year,t.month,t.day)
  
  end

end
