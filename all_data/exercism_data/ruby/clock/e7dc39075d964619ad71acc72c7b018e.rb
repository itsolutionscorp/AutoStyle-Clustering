class Clock
  attr_accessor :minutes_today

  MINS_PER_HOUR = 60
  MINS_PER_DAY = 24 * MINS_PER_HOUR
  
  def self.at(hour, minute=0)
    Clock.new(hour, minute)
  end

  def initialize(hour, minute=0)
    @minutes_today = MINS_PER_HOUR * hour + minute
    @minutes_today %= MINS_PER_DAY
  end

  def to_s
    hh = @minutes_today / MINS_PER_HOUR
    mm = @minutes_today % MINS_PER_HOUR
    sprintf("%02d:%02d", hh, mm)
  end

  def +(minutes)
    @minutes_today += minutes
    @minutes_today %= MINS_PER_DAY
    self
  end

  def -(minutes)
    self.+(-minutes)
  end

  def ==(other)
    @minutes_today == other.minutes_today
  end
end
