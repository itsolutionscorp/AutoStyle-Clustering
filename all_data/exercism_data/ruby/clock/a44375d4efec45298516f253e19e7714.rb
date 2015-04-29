class Clock
  include Comparable

  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24
  STRING_FORMAT = "%02d:%02d"

  attr_reader :hour, :minute
  def initialize(hour, minute = 0)
    @hour = hour;   @minute = minute
  end

  def add(hours: 0, minutes: 0)
    add_hours hours;  add_minutes minutes
  end

  def add_minutes(minutes)
    @minute += minutes
    if minute >= MINUTES_PER_HOUR
      add hours: 1,  minutes: -MINUTES_PER_HOUR
    elsif minute < 0
      add hours: -1, minutes: MINUTES_PER_HOUR
    end
    self
  end

  def add_hours(hours)
    @hour += hours
    if hour >= HOURS_PER_DAY
      add_hours -HOURS_PER_DAY
    elsif hour < 0
      add_hours HOURS_PER_DAY
    end
    self
  end
  
  alias :+ :add_minutes
  def -(minutes); add_minutes(-minutes); end

  def <=>(other)
    (hour <=> other.hour).zero? ? minute <=> other.minute : hour <=> other.hour
  end

  def to_s; sprintf(STRING_FORMAT, hour, minute); end

  def self.at(*args); new(*args); end

end
