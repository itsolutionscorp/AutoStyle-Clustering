class Clock

  def self.at(hour, minute=0)
    ClockTime.new(hour, minute)
  end

end

class ClockTime

  attr_reader :hours, :minutes

  def initialize(hour, minute)
    @hours, @minutes = extract_times(hour, minute)
  end

  def to_s
    "#{hours.to_s.rjust(2, "0")}:#{minutes.to_s.rjust(2, "0")}"
  end

  def +(additional_minutes)
    ClockTime.new(hours, minutes + additional_minutes)
  end

  def -(reduced_minutes)
    ClockTime.new(hours, minutes - reduced_minutes)
  end

  def ==(other)
    (hours + minutes) == (other.hours + other.minutes)
  end

  private

  def extract_times(hour, minute)
    extra_hours, new_minutes = minute.divmod(60)
    new_hours = (hour + extra_hours) % 24
    [new_hours, new_minutes]
  end

end
