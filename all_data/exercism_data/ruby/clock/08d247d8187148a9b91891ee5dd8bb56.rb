class Clock
  def initialize(hours, minutes=0)
    hours += minutes / 60
    hours %= 24
    minutes %= 60
    @hours = hours
    @minutes = minutes
  end

  def self.at(hours, minutes=0)
    Clock.new(hours, minutes)
  end

  attr_reader :hours, :minutes

  def to_s
    "%02d:%02d" % [@hours, @minutes]
  end

  def +(mins)
    Clock.new(@hours, @minutes+mins)
  end

  def -(mins)
    Clock.new(@hours, @minutes-mins)
  end

  def ==(other)
    @minutes == other.minutes && @hours == other.hours
  end

end
