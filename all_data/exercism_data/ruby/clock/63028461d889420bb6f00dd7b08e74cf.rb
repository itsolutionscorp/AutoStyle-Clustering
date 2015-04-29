class Clock
  include Comparable

  attr_reader :hours, :minutes

  class << self
    alias :at :new
  end

  def initialize(hours = 0, minutes = 0)
    @hours, @minutes = hours, minutes
  end

  def +(minute_offset)
    hour_offset, @minutes = (@minutes + minute_offset).divmod(60)
    @hours = (@hours + hour_offset) % 24

    self
  end

  def -(offset)
    self + -offset
  end

  def <=>(other)
    comp = hours <=> other.hours
    comp.zero? ? minutes <=> other.minutes : comp
  end

  def to_s
    "%02d:%02d" % [@hours, @minutes]
  end
end
