class Clock

  attr_reader :hours, :minutes

  def initialize(hours, minutes = 0)
    @hours = hours
    @minutes = minutes
  end

  def self.at(hours, minutes = 0)
    self.new(hours, minutes)
  end

  def to_s
    "%02d:%02d" % [@hours % 24, @minutes % 60]
  end

  def +(min)
    new_time = min.divmod(60)
    hours = @hours + new_time[0]
    minutes = @minutes + new_time[1]
    self.class.at(hours, minutes)
  end

  def -(min)
    new_time = min.divmod(-60)
    hours = @hours + new_time[0]
    minutes = @minutes + new_time[1]
    self.class.at(hours, minutes)
  end

  def ==(other)
    @hours == other.hours && @minutes == other.minutes
  end

end
