require 'time'

class Clock

  def initialize(clock)
    @clock = clock
  end

  def self.at(hour, minute = 0)
    result = convert_to_time(hour, minute)
    Clock.new(result)
  end

  def +(minutes)
    @clock += (minutes * 60)
    self
  end

  def -(minutes)
    @clock -= (minutes * 60)
    self
  end

  def to_s
    @clock.strftime("%H:%M")
  end

  def ==(other)
    self.to_s == other.to_s
  end

  private
  def self.convert_to_time(hour, minute)
    Time.parse("%02d" % hour + ":" + "%02d" % minute)
  end
end
