class Clock
  attr_reader :hours, :minutes
  def initialize(hours, minutes)
    @hours = hours
    @minutes = minutes
  end

  def to_s
    "#{hours.to_s.rjust(2, "0")}:#{minutes.to_s.rjust(2, "0")}"
  end

  def + (minutes)
    @minutes += minutes
    fix_minutes
  end

  def - (minutes)
    @minutes -= minutes
    fix_minutes
  end

  def fix_minutes
    until (0...60).member? minutes
      @hours -= 60 <=> minutes
      @minutes += 60 * (60 <=> minutes)
    end
    @hours %= 24
    self
  end

  def == (other_clock)
    hours == other_clock.hours && minutes == other_clock.minutes
  end

  def self.at(hours, minutes = 0)
    Clock.new(hours, minutes)
  end
end
