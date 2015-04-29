class Clock
  class << self
    alias_method :at, :new
  end

  def initialize(hours, minutes = 0)
    @minutes = (hours * 60 + minutes) % (24 * 60)
  end

  def +(other)
    self.class.at(0, @minutes + other)
  end

  def -(other)
    self.class.at(0, @minutes - other)
  end

  def to_s
    '%02d:%02d' %  @minutes.divmod(60)
  end

  def ==(other)
    to_s == other.to_s
  end
end
