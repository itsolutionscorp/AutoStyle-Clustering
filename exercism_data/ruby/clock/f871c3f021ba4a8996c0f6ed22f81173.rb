class Clock
  attr_accessor :hours, :minutes
  
  def initialize(hours, minutes)
    @hours = hours
    @minutes = minutes
  end

  def self.at hours, minutes = 0
    new hours, minutes
  end

  def + min
    calc '+', min
    self
  end

  def - min
    calc '-', min
    self
  end

  def to_s
    sprintf "%02d:%02d", @hours, @minutes
  end

  def eql? other
     other.instance_of?(self.class) && self.hours == other.hours && self.minutes == other.minutes
  end

  def == other
    self.eql? other
  end

  private
  def calc symbol, min
    @hours = ( @hours + (@minutes.send(symbol, min ) ) / 60 ) % 24
    @minutes = ( @minutes.send(symbol, min ) ) % 60
  end
end
