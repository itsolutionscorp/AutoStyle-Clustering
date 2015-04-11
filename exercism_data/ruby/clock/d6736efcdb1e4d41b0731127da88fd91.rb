class Clock
  def self.at hour = 0, minute = 0
    Clock.new(hour, minute)
  end
  
  def initialize hour = 0, minute = 0
    @hour = hour
    @minute = minute
    @hour, @minute = hours_and_minutes minutes
  end
  
  def +(mins)
    @hour, @minute = hours_and_minutes(minutes + mins)
    self
  end
  
  def -(mins)
    @hour, @minute = hours_and_minutes(minutes - mins)
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
  
  def hours_and_minutes minutes
    (minutes % (24*60)).divmod 60
  end
end
