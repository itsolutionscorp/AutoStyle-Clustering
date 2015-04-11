class Clock
  attr_reader :time

  HOUR = 60
  DAY  = 24

  class << self
    alias_method :at, :new
  end

  def initialize(hr,min=0)
    @time  = (hr * 60) + min
  end

  def to_s
    sprintf("%02d:%02d", hr, min)
  end

  def +(min)
    @time += min
    self
  end

  def -(min)
    @time -= min
    self
  end

  def ==(other)
    time == other.time
  end

  private

  def hr
    @hr = (time / HOUR) % DAY
  end

  def min
    @min = time % HOUR
  end
end
