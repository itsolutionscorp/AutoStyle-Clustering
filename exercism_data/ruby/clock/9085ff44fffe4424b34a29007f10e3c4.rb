class Clock
  def self.at hour = 0, minute = 0
    Clock.new(hour, minute)
  end
  
  def initialize hour = 0, minute = 0
    @hour, @minute = hour.to_i, minute.to_i
    @hour, @minute = hour_and_minute
  end
  
  def +(mins)
    @hour, @minute = hour_and_minute(minutes + mins)
    self
  end
  
  def -(mins)
    @hour, @minute = hour_and_minute(minutes - mins)
    self
  end
  
  def == other
    self.to_i == other.to_i
  end
  
  def to_s
    sprintf "%02d:%02d", @hour, @minute
  end
  
  def to_i
    minutes
  end
  
  private
  
  def minutes
    @hour*60+@minute
  end
  
  def hour_and_minute minutes = minutes
    (minutes % (24*60)).divmod 60
  end
end
