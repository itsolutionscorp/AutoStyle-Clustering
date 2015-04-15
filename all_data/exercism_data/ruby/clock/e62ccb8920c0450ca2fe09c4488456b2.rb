class Clock
  attr_reader :minutes

  def self.at hours, minutes = 0
    new(hours * 60 + minutes)
  end

  def initialize minutes
    @minutes = minutes
  end

  def + minutes
    self.class.new((@minutes + minutes) % 1440)
  end

  def - minutes
    self + -minutes
  end

  def == other
    minutes == other.minutes
  end

  def to_s
    "%02d:%02d" % minutes.divmod(60)
  end
end
