require 'time'

class Clock
  attr_accessor :time
  def initialize(time)
    @time = time
  end

  def self.at(hour, minutes=0)
    Clock.new(Time.new(0, 1, 1, hour, minutes))
  end

  def to_s
    time.strftime("%H:%M")
  end

  def + minutes
    self.class.new(time + minutes * 60)
  end

  def - minutes
    self + (minutes * -1)
  end

  def ==(clock)
    to_s == clock.to_s
  end

  def min
    time.min
  end

  def hour
    time.hour
  end
end

