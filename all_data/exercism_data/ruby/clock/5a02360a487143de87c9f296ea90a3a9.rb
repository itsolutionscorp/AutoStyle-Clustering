class Clock
  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hour, minute = 0)
    @time = Time.new(0, 1, 1, 0, 0, 0)
    @time += hour * 3600 + minute * 60
  end

  def +(minute)
    self.class.new(@time.hour, @time.min + minute)
  end

  def -(minute)
    self + -1 * minute
  end

  def ==(other)
    self.to_s == other.to_s
  end

  def to_s
    @time.strftime("%H:%M")
  end
end
