class Clock
  attr_accessor :hour, :minutes
  
  def initialize hour, minutes = 0
    @hour = hour
    @minutes = minutes
  end

  def self.at hour, minutes = 0
    new hour, minutes
  end

  def to_s
    display_hour << ":" << display_minutes
  end

  def display_hour
    sprintf "%02d", hour
  end


  def display_minutes
    sprintf "%02d", minutes
  end

  def + more_minutes
    self.minutes += more_minutes
    if minutes > 60
      add_hour
    end
    fix_day
    self
  end

  def - less_minutes
    self.minutes -= less_minutes
    until self.minutes > 0
      subtract_hour
    end
    fix_day
    self
  end
  
  def == other_clock
    self.to_s == other_clock.to_s
  end

  private

  def fix_day
    self.hour -= 24 if hour >= 24
    self.hour += 24 if hour < 0
  end
  def add_hour
      self.hour += 1 and self.minutes -= 60
  end

  def subtract_hour
      self.hour -= 1 and self.minutes += 60
  end
  
end
