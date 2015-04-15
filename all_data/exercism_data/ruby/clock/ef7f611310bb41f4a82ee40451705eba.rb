class Clock
  def self.at(hours, minutes=0)
    new(hours, minutes)
  end

  attr_reader :seconds_since_epoch

  def initialize(hours, minutes)
    @seconds_since_epoch = Time.utc(1970, 1, 1, hours, minutes)
  end

  def +(minutes)
    @seconds_since_epoch += (minutes * 60)
    self
  end

  def -(minutes)
    @seconds_since_epoch -= (minutes * 60)
    self
  end

  def ==(rhs)
    seconds_since_epoch == rhs.seconds_since_epoch
  end

  def to_s
    seconds_since_epoch.strftime('%H:%M')
  end
end
