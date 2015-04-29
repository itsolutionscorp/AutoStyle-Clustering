class Clock
  
  def self.at(hours, minutes=0)
    self.new(hours, minutes)
  end
  
  NO_OF_MINUTES_IN_AN_HOUR = 60
  NO_OF_HOURS_IN_A_DAY = 24
  
  attr_reader :hours, :minutes
  
  def initialize(hours, minutes=0)
    hours = Integer(hours)
    minutes = Integer(minutes)
    throw ArgumentError if (hours >= NO_OF_HOURS_IN_A_DAY || minutes >= NO_OF_MINUTES_IN_AN_HOUR)
    @hours = Hours.new(hours)
    @minutes = minutes
  end
  
  def +(extra_minutes)
    hrs, mins = (minutes + extra_minutes).divmod(NO_OF_MINUTES_IN_AN_HOUR)
    self.class.at(hours+hrs, mins)
  end
  
  def -(fewer_minutes)
    hrs, mins = (minutes - fewer_minutes).divmod(NO_OF_MINUTES_IN_AN_HOUR)
    self.class.new(hours + hrs, mins)
  end
  
  def ==(other)
    hours == other.hours && minutes == other.minutes
  end
  
  def to_s
    "%02d:%02d" % [hours, minutes]
  end
  
  private
  
  class Hours
    
      NO_OF_HOURS_IN_A_DAY = 24
    
    def initialize hours
      @hours = hours
    end
    
    def +(hrs)
      (@hours + hrs) % NO_OF_HOURS_IN_A_DAY
    end
    
    def to_i
      @hours
    end
    
    def ==(other)
      self.to_i == other.to_i
    end
    
  end
  
end
