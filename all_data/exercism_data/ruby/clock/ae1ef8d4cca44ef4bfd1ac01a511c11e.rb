class Clock
  include Comparable

  def self.at(hour, minute = 0)
    new(60 * hour + minute)
  end

  def initialize(minutes)
    @minutes = minutes.modulo(60 * 24)
  end

  def +(other)
    self.class.new(@minutes + other)
  end

  def -(other)
    self.class.new(@minutes - other)
  end

  def to_s
    format '%02d:%02d', *@minutes.divmod(60)
  end

  def <=>(other)
    to_s <=> other.to_s
  end
end
