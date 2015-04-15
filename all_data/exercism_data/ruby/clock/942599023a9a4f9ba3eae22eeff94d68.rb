class Clock
  attr_reader :hour, :min
  
  def self.at(hour, min=0)
    Clock.new(hour, min)
  end
  
  def initialize(hour, min=0)
    @hour, @min = hour, min
  end
  
  def to_s
    sprintf('%02d', hour) + ":" + sprintf('%02d', min)
  end
  
  def ==(clock_2)
    true if self.hour == clock_2.hour && self.min == clock_2.min
  end
  
  def +(num)
    adjust_clock((@hour * 60) + @min + num)
  end
  
  def -(num)
    adjust_clock((@hour * 60) + @min - num)
  end
  
  private
  
  def adjust_clock(total_mins)
    @hour = total_mins/60
    @hour = @hour + 24 if @hour < 0
    @hour = @hour - 24 if @hour >= 24
    @min = total_mins % 60
    to_s
  end
end
