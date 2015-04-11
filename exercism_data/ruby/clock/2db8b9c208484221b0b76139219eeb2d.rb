class Clock
  def self.at(*args)
    new(*args)
  end

  attr_reader :hours, :minutes
  def initialize(hours, minutes=0)
    time = hours*60 + minutes
    @hours = time / 60 % 24
    @minutes = time % 60
  end

  def to_s
    "#{hours.to_s.rjust(2,'0')}:#{minutes.to_s.rjust(2,'0')}"
  end

  def +(mins)
    Clock.new(hours, minutes + mins)
  end

  def -(mins)
    Clock.new(hours, minutes - mins)
  end

  def ==(other)
    [hours, minutes] == [other.hours, other.minutes]
  end
end
