class Clock
  MINUTES_PER_DAY = 1440 

  def self.at(hours, minutes=0)
    new(hours * 60 + minutes)
  end

  def initialize(minutes)
    @minutes = minutes % MINUTES_PER_DAY
  end

  def to_s
    hours, minutes = @minutes.divmod(60).map { |n| n.to_s.rjust(2, "0") }
    "#{hours}:#{minutes}"
  end

  def +(minutes)
    Clock.new(@minutes + minutes)
  end

  def -(minutes)
    Clock.new(@minutes - minutes)
  end

  def ==(other_clock)
    to_s == other_clock.to_s
  end
end
