class Gigasecond
  
  GS = 1e9
  
  def self.from(time)
    (time + GS)
  end
  
end

puts Gigasecond.from(Time.utc(1986, 2, 6))
