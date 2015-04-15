class Clock

  attr_reader :hours, :minutes

  def initialize hours, minutes
    @hours   = hours
    @minutes = minutes
    opperate_time
  end

  def self.at hours, minutes = 0
    new( hours, minutes )
  end

  def to_s
    "#{formated_hours}:#{formated_minutes}"
  end

  def + minutes_add
    Clock.new( hours, minutes + minutes_add )
  end

  def - minutes_substract
    Clock.new( hours, minutes - minutes_substract )
  end

  def == other
    if self.hours == other.hours and self.minutes == other.minutes
      [ self, other ]
    end
  end

private
  
  attr_writer :hours, :minutes

  def formated_hours
    hours.to_s.rjust( 2, '0' )
  end

  def formated_minutes
    minutes.to_s.rjust( 2, '0' )
  end

  def opperate_time
    recalculate_minutes_overload  if minutes >= 60
    recalculate_minutes_underload if minutes < 0
    recalculate_hours_overload    if hours   >= 24
    recalculate_hours_underload   if hours   < 0
  end

  def recalculate_minutes_overload
    while minutes >= 60
      self.minutes = minutes - 60 
      self.hours   = hours + 1
    end
  end

  def recalculate_hours_overload
    while hours >= 24
      self.hours = hours - 24
    end
  end

  def recalculate_minutes_underload
    while minutes < 0
      self.minutes = minutes + 60
      self.hours   = hours - 1
    end
  end

  def recalculate_hours_underload
    while hours < 0
      self.hours = hours + 24
    end
  end

end
