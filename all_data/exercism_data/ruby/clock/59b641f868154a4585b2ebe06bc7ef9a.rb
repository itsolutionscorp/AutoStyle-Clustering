class Clock
  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hour, minute)
    # The date is an easter egg. Figure it out :)
    # We use UTC so there's no DST to trip us up.
    @time = Time.utc(1993, 2, 24, hour, minute)
  end

  def +(add_minutes)
    new_time = @time + add_minutes * 60
    self.class.new(new_time.hour, new_time.min)
  end

  def -(subtract_minutes)
    self.+(-subtract_minutes)
  end

  def ==(other)
    to_s == other.to_s
  end

  def to_s
    @time.strftime("%H:%M")
  end
end
