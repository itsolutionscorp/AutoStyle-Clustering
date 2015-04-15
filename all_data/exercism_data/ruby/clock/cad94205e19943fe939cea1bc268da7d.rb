class Clock < Struct.new(:minutes)
  HOUR = 60
  DAY = 24*HOUR

  def self.at(hours, minutes = 0)
    new(HOUR * hours + minutes)
  end

  def +(minutes)
    Clock.new((self.minutes += minutes) % DAY)
  end

  def -(minutes)
    Clock.new((self.minutes -= minutes) % DAY)
  end

  def to_s
    "#{formatted_hours}:#{formatted_minutes}"
  end

  private

  def formatted_hours
    format(minutes / HOUR)
  end

  def formatted_minutes
    format(minutes % HOUR)
  end

  def format(duration)
    duration.to_s.rjust(2, '0')
  end
end
