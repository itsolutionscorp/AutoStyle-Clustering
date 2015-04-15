class Clock
  attr_reader :hours, :minutes

  def initialize(hours = 0, minutes = 0)
    @hours = hours
    @minutes = minutes
    normalize
  end

  def self.at(*time)
    new(*time)
  end

  def to_s
    "%02d:%02d" % [hours, minutes]
  end

  def +(add_minutes)
    self.class.at(hours, minutes + add_minutes)
  end

  def -(sub_minutes)
    self.class.at(hours, minutes - sub_minutes)
  end

  def ==(other)
    hours == other.hours &&
      minutes == other.minutes
  end

  private

  MIN_PER_HOUR = 60
  HOURS_PER_DAY = 24

  def normalize
    additional_hours, @minutes = minutes.divmod(MIN_PER_HOUR)
    @hours = (hours + additional_hours) % HOURS_PER_DAY
  end
end
