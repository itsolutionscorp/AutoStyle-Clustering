class Clock
  private_class_method :new

  def self.at *time
    new(*time)
  end

  def initialize *time
    raise ArgumentError, '2 arguments max' if time.length > 2

    # can be simpler, but I want to handle some unexpected arguments
    minutes = time.length == 1 ? time[0].to_i * 60 : time.map(&:to_i).inject { |s, i| s * 60 + i }

    @hour, @min = minutes_to_time(minutes)
  end

  def to_s
    [@hour, @min].map { |i| i.to_s.rjust(2, '0') }.join(':')
  end

  def to_minutes
    @hour * 60 + @min
  end

  # operation override

  def +(minutes)
    # 2 Clock object can plus each other
    minutes = minutes.to_minutes if minutes.is_a? Clock

    @hour, @min = minutes_to_time(to_minutes + minutes)
    self
  end

  def -(minutes)
    # 2 Clock object can minus each other
    minutes = minutes.to_minutes if minutes.is_a? Clock

    @hour, @min = minutes_to_time(to_minutes - minutes)
    self
  end

  def ==(other)
    self.to_minutes == other.to_minutes
  end

  private

  def minutes_to_time minutes
    minutes %= 24 * 60
    hours = minutes / 60
    mins = minutes - hours * 60
    [hours, mins]
  end
end
