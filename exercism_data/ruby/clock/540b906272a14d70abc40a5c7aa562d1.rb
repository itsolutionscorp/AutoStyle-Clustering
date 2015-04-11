class Clock
  
  def self.at(hours, minutes=0)
    self.new(hours, minutes)
  end
  
  attr_reader :hours, :minutes
  
  def initialize(hours, minutes=0)
    @hours = hours
    @minutes = minutes
  end
  
  def +(minutes)
    total_minutes = current_total_minutes + minutes
    self.class.new((total_minutes / 60) % 24, total_minutes % 60)
  end
  
  def -(minutes)
    total_minutes = current_total_minutes - minutes
    self.class.new((total_minutes / 60) % 24, total_minutes % 60)
  end
  
  def ==(other)
    hours == other.hours && minutes == other.minutes
  end
  
  def to_s
    "%02d:%02d" % [@hours, @minutes]
  end
  
  private
  
  def current_total_minutes 
    ((@hours * 60) + @minutes)
  end
  
end
