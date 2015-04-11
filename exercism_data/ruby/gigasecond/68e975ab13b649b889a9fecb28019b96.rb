class Gigasecond
  GIGASECOND = 1000000000 # 10**9 
  
  def self.from date
    Time.at(Integer(date) + GIGASECOND)
  end
end
