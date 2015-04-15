class Clock
  attr_reader :minutes

  def self.at(*time)
    new(*time)
  end

  def initialize(*time)
    raise ArgumentError, '2 arguments max' if time.length > 2

    @minutes = time.length == 1 ? time[0] * 60 : time.reduce { |s, i| s * 60 + i }
    @minutes %= 24 * 60
  end

  def to_s
    "%02d:%02d" % [hour, min]
  end

  # operation override

  def +(minutes)
    Clock.at(hour, min + minutes)
  end

  def -(minutes)
    Clock.at(hour, min - minutes)
  end

  def ==(other)
    self.minutes == other.minutes
  end

  private

  def hour
    @hour ||= minutes / 60
  end

  def min
    @min ||= minutes % 60
  end
end
