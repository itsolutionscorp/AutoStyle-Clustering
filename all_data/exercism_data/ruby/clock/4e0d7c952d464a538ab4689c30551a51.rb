class Clock
  attr_reader :minutes

  def initialize minutes
    @minutes = minutes
  end

  def self.at hours, minutes = 0
    Clock.new (hours * 60 + minutes)
  end

  def + minutes
    Clock.new ((@minutes + minutes) % 1440)
  end

  def - minutes
    self + -minutes
  end

  def == other
    @minutes == other.minutes
  end

  def to_s
    @minutes.divmod(60).map { |x| x.to_s.rjust(2, '0') }.join ':'
  end
end
