class Clock
  attr_accessor :time, :hour, :minute
  class << self
    def at(hour=0,minute=0)
      Clock.new(hour,minute)
    end
  end

  def initialize(hour,minute)
    self.time=minute+hour*60
  end

  def +(val)
    self.time+=val
    self
  end

  def -(val)
    self.time-=val
    self
  end

  def ==(val)
    to_s==val.to_s
  end

  def normalize
    self.hour,self.minute=(time/60)%24,time%60
  end

  def to_s
    normalize
    "%02d:%02d" % [hour,minute]
  end
end
