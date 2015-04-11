class Clock
  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hours, minutes)
    @minutes = hours * 60 + minutes
  end

  def +(add_minutes)
    self.class.new(0, @minutes + add_minutes)
  end

  def -(subtract_minutes)
    self.+(-subtract_minutes)
  end

  def ==(other)
    to_s == other.to_s
  end

  def to_s
    format("%02d:%02d", hour, minute)
  end

  private

  def hour
    @minutes / 60 % 24
  end

  def minute
    @minutes % 60
  end
end
