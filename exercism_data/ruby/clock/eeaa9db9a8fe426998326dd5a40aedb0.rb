class Clock

  include Comparable

  attr_reader :hour
  attr_reader :minutes


  def <=>(other)
    corrected_time <=> other.corrected_time
  end

  def self.at(hour, minutes = 0)
    new(hour, minutes)
  end

  def initialize(hour, minutes)  
    @hour = hour
    @minutes = minutes
    corrected_time
  end

  def to_s
    time_hours = "%.2i" %(corrected_time / 60)
    time_minutes = "%.2i" %(corrected_time - time_hours.to_i * 60)
    time_hours + ":" + time_minutes

  end

  def corrected_time
    if (0..1440).include?(convert_to_minutes(hour, minutes))
      convert_to_minutes(hour, minutes)
    else
      wrap_time(convert_to_minutes(hour, minutes))
    end
  end

  def +(additional_minutes)
    Clock.new(hour, (minutes + additional_minutes))
  end

  def -(additional_minutes)
    Clock.new(hour, (minutes - additional_minutes))
  end

  def wrap_time(minutes)
    if minutes > 1440
      minutes - 1440
    else 
      1440 - minutes.abs
    end
  end

  def convert_to_minutes(hour, minutes)
    hour * 60 + minutes
  end

end
