class Clock
  attr_reader :hour, :minute

  def initialize(hour, minute)
    @hour = hour
    @minute = minute

    handle_overflow
  end

  def self.at(hour, minute = 0)
    Clock.new(hour, minute)
  end

  def +(minutes)
    Clock.new(hour, minute + minutes)
  end

  def -(minutes)
    self + (-minutes)
  end

  def ==(other)
    hour == other.hour && minute == other.minute
  end

  def to_s
    sprintf("%02d:%02d", hour, minute)
  end

  private

  def handle_overflow
    extra_hours, @minute = minute.divmod(60)
    @hour = (hour + extra_hours) % 24
  end
end
