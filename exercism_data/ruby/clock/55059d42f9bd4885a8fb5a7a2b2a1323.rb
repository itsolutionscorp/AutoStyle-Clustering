class Clock
  attr_reader :hour, :minute
  def initialize hour, minute=0
    @hour_hand = hour
    @minute_hand = minute
  end

  def self.at(hour, minute=0)
    Clock.new(hour,minute)
  end
  
  def + minutes
    self.class.new((@hour_hand + minutes/60) % 24, @minute_hand + minutes % 60) 
  end

  def - minutes
    self + (minutes * -1)
  end

  def == clock
    to_s == clock.to_s
  end

  def to_s
    "%02d:%02d" % [@hour_hand, @minute_hand]
  end
end