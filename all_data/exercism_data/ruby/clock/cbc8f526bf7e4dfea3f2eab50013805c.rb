require 'pry'
class Clock
  attr_accessor :seconds

  def self.at time, mins = 0
    new.at(time, mins)
  end

  def at time, mins
    @seconds = clock_in_seconds(time, mins)
    self
  end

  def to_s
    set_clock.strftime("%H:%M")
  end

  def == clock
    self.seconds == clock.seconds
  end

  private

  def method_missing operator, mins
    set_new_time(operator, mins)
  end

  def set_new_time operator, mins
    @seconds = @seconds.send(operator, mins * 60)
    self
  end

  def set_clock
    Time.at(@seconds)
  end

  def clock_in_seconds time, mins
    (time + 3) * 3600 + mins * 60
  end
end
