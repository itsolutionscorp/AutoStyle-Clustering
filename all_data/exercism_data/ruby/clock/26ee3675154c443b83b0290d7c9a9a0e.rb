class Clock
  include Comparable
  
  def self.at hour = 0, minute = 0
    Clock.new(hour, minute)
  end
  
  def initialize hour = 0, minute = 0
    @hour, @minute = hour.to_i, minute.to_i
    @hour, @minute = hour_and_minute minutes
  end
  
  def + mins
    Clock.at *hour_and_minute(minutes + mins.to_i)
  end
  
  def - mins
    Clock.at *hour_and_minute(minutes - mins.to_i)
  end
  
  def <=> other
    self.to_i <=> other.to_i
  end
  
  def to_i
    minutes
  end
  
  def to_s
    sprintf "%02d:%02d", @hour, @minute
  end
  
  private
  
  def minutes
    @hour*60+@minute
  end
  
  def hour_and_minute minutes
    (minutes % (24*60)).divmod 60
  end
end
