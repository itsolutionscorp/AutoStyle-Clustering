class Clock
  MINUTES_PER_DAY = 1440
  FORMAT = "%02d:%02d"

  def self.at(hours, minutes=0)
    new(hours * 60 + minutes)
  end

  def initialize(minutes)
    @minutes = minutes % MINUTES_PER_DAY
  end

  def to_s
    FORMAT % @minutes.divmod(60)
  end

  def +(minutes)
    self.class.new(@minutes + minutes)
  end

  def -(minutes)
    self.class.new(@minutes - minutes)
  end

  def ==(other_clock)
    to_s == other_clock.to_s
  end
end
