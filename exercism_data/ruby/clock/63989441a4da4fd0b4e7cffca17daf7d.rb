class Clock
  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hour, minute)
    @hour = hour
    @minute = minute
  end

  def to_s
    "%02d:%02d" % [hour, minute]
  end

  def +(minutes)
    hours, minutes = parse(minutes)
    hours -= 24 if hour + hours >= 24
    self.class.new(hour + hours, minute + minutes)
  end

  def -(minutes)
    hours, minutes = parse(minutes)
    hours -= 24 if hour - hours < 0
    if minutes > minute
      hours += 1
      minutes -= 60
    end
    self.class.new(hour - hours, minute - minutes)
  end

  def ==(other_clock)
    hour == other_clock.hour && minute == other_clock.minute
  end

  protected

  attr_reader :hour, :minute

  private

  def parse(minutes)
    [minutes / 60, minutes.remainder(60)]
  end
end
