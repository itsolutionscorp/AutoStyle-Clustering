class Clock
  attr_accessor :minutes_from_noon
  def initialize(hour, minute)
    reset_minutes_from_noon(hour * 60 + minute)
  end
  def self.at(hour = 0, minute = 0)
    Clock.new(hour, minute)
  end

  def hour
    minutes_from_noon / 60
  end

  def minute
    minutes_from_noon % 60
  end

  def to_s
    sprintf('%02d:%02d', hour, minute)
  end

  def +(minutes_to_add)
    reset_minutes_from_noon(@minutes_from_noon + minutes_to_add)
  end

  def -(minutes_to_subtract)
    reset_minutes_from_noon(@minutes_from_noon - minutes_to_subtract)
  end

  def reset_minutes_from_noon(new_value)
    new_value = new_value - 1440 if new_value > 1440
    new_value = new_value + 1440 if new_value < 0
    @minutes_from_noon = new_value
    self
  end

  def ==(other)
    minutes_from_noon == other.minutes_from_noon
  end
end
