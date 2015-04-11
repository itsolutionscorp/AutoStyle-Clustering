class Clock
  class << self
    def at(hours, minutes=0)
      minutes_past_might = (hours*Clock::MINUTES_IN_ONE_HOUR) + minutes
      new(minutes_past_might)
    end
  end

  include Comparable

  attr_reader :minutes

  MINUTES_IN_ONE_HOUR = 60
  HOURS_IN_ONE_DAY = 24
  MINUTES_IN_ONE_DAY = MINUTES_IN_ONE_HOUR*HOURS_IN_ONE_DAY

  def initialize(minutes)
    @minutes = minutes
  end

  def +(minutes_to_add)
    @minutes += minutes_to_add
    self
  end

  def -(minutes_to_subtract)
    @minutes -= minutes_to_subtract
    self
  end

  def to_s
    "#{hours_to_string}:#{minutes_to_string}"
  end

  def <=>(other)
    minutes <=> other.minutes
  end

  private

  def hours_past_midnight
    (minutes.to_i % MINUTES_IN_ONE_DAY) / MINUTES_IN_ONE_HOUR
  end

  def minutes_past_the_hour
    (minutes.to_i% MINUTES_IN_ONE_DAY) % MINUTES_IN_ONE_HOUR
  end

  def hours_to_string
    "%02d" % hours_past_midnight
  end

  def minutes_to_string
    "%02d" % minutes_past_the_hour
  end
end
