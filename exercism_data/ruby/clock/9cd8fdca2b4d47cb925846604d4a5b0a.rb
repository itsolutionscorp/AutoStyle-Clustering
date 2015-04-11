class Clock

  attr_reader :hour, :min, :class

  def initialize hour, min
    @hour = hour
    @min  = min
  end

  def self.at hour, min=0
    @class = Clock.new hour, min 
  end  

  def to_s
     "%02d:%02d" % [@hour, @min] 
  end

  def + num
    @hour+= num / 60
    @min += num % 60 

    @hour = (@hour >= 24) ? @hour % 24 : @hour
    self
  end

  def -(num)
    @hour -= (num / 60.0).ceil
    @min  -= num % 60
    @min = @min.abs if @min <= 0
    
    @hour = (@hour < 0) ? 24 + @hour : @hour
    self
  end

  def == clock
    hour == clock.hour and min == clock.min
  end

end
