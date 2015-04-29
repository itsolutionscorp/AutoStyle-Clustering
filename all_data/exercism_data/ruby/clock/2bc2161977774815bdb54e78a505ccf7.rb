class Clock
  def self.at(*args)
    new(*args)
  end

  attr_reader :hour, :minute

  def initialize(hour, minute = 0)
    @hour = hour
    @minute = minute
  end

  def to_s
    "%02d:%02d" % [hour, minute]
  end

  def +(mins)
    h, m = adjust_clock(hour, minute + mins)
    self.class.new(h, m)
  end

  def -(mins)
    h, m = adjust_clock(hour, minute - mins)
    self.class.new(h, m)
  end

  def ==(other)
    hour == other.hour && minute == other.minute
  end

  private

  def adjust_clock(hour, minute)
    while minute < 0
      minute += 60
      hour -= 1
    end

    while minute > 59
      minute -= 60
      hour += 1
    end

    if hour < 0
      hour = 23
    end

    if hour > 23
      hour = 0
    end

    [hour, minute]
  end
end
