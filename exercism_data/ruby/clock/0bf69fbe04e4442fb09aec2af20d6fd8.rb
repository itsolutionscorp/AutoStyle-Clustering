class Clock
  attr_reader :hour, :minute

  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hour, minute)
    @hour = hour
    @minute = minute
  end

  def +(minutes)
    overflow, remainder = (minute + minutes).divmod(60)
    @hour = (hour + overflow) % 24
    @minute = remainder
    self
  end

  def -(minutes)
    send(:+, -minutes)
  end

  def ==(other)
    other.hour == hour && other.minute == minute
  end

  def to_s
    format("%02d:%02d", hour, minute)
  end
end
