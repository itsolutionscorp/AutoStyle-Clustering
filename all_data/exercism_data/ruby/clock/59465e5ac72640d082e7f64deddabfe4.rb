class Clock
  attr_reader :minutes

  def initialize(minutes)
    @minutes = minutes
  end

  def self.at(hour, minute=0)
    new(hour*60 + minute)
  end

  def +(val)
    Clock.new(@minutes + val)
  end

  def to_s
    @minutes %= (60*24)
    "%02d:%02d" % [ @minutes / 60, @minutes % 60]
  end

  def -(val)
    Clock.new(@minutes - val)
  end

  def ==(obj)
    self.minutes == obj.minutes
  end

end
