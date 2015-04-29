class Clock
  
  attr_accessor :hour, :minute
  
  def initialize(hour, minute)
    self.hour = hour
    self.minute = minute
  end  
  
  def self.at(h=nil,m=nil)
    h||=0
    m||=0
    Clock.new(h,m)
  end 
  
  def +(minute)
    computed_minutes = self.minute + minute
    if (computed_minutes > 60)
      self.minute = computed_minutes % 60
      self.hour += computed_minutes / 60
    else
      self.minute = computed_minutes 
    end
    if (self.hour >=24)
      self.hour = self.hour - 24
    end     
    self
  end 
  
  def -(minute)
    computed_minutes = self.minute - minute
    if (computed_minutes < 0)
      self.minute = (computed_minutes % 60).abs
      self.hour += computed_minutes / 60
    end  
    if (self.hour < 0)
      self.hour = 24 + self.hour
    end  
    self
  end 
  
  def format(n)
    n >= 10 ? n.to_s : "0#{n}"
  end  
  
  def to_s
    "#{format(hour)}:#{format(minute)}"    
  end 
  
  def ==(clock)
    return self && clock && self.hour ==clock.hour && self.minute == clock.minute     
  end   

end  
