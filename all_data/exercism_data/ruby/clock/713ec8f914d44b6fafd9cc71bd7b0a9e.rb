class Clock
  def initialize(time)
    @time = time
  end
  attr_accessor :time

  def self.at(hour, minute=0)
    Clock.new(Time.new 0, 1, 1, hour, minute)
  end

  def to_s
    "%02d:%02d" % [ @time.hour, @time.min ]
  end

  def +(mins)
    self.class.new(@time + mins * 60)
  end

  def -(mins)
    self + (mins * -1)
  end

  def ==(clock)
    to_s == clock.to_s
  end
end
