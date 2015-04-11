class Gigasecond 
  def self.from(event)  
    Time.at(event) + 10 ** 9   # 10 ** 9 more seconds
  end 
end
