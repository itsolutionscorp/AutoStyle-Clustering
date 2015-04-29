class Gigasecond
  def self.time_constant
    10**9
  end  
  
  def self.from(birthday)
    return birthday + time_constant
  end
end
