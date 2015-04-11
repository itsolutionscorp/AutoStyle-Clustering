require 'byebug'

class Clock
  MINUTES_PER_DAY = 1440

  def self.at(hours, minutes = 0)
    new(hours, minutes)
  end

  def initialize(hours, minutes)
    @minutes = hours * 60 + minutes
  end

  def to_s
    hours, minutes = @minutes.divmod(60)
    sprintf("%02d:%02d", hours, minutes)
  end

  def +(minutes)
    @minutes += minutes
    @minutes -= MINUTES_PER_DAY if @minutes >= MINUTES_PER_DAY
    self
  end

  def -(minutes)
    @minutes -= minutes
    @minutes += MINUTES_PER_DAY if @minutes < 0
    self
  end

  def ==(clock)
    @minutes == clock.minutes
  end

  protected

  def minutes
    @minutes
  end
end
