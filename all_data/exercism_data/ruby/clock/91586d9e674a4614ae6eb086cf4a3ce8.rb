class Clock
  def self.at(hours, minutes = 0)
    new(hours, minutes)
  end

  attr_accessor :minutes

  def initialize(hours, minutes = 0)
    @minutes = hours * 60
    @minutes += minutes
  end

  def to_s
    "#{justified_hours}:#{justified_minutes}"
  end

  def +(n)
    tap {|clock| clock.minutes += n }
  end

  def -(n)
    tap {|clock| clock.minutes -= n }
  end

  def ==(other)
    minutes == other.minutes
  end

  private

  def hours
    (minutes / 60) % 24
  end

  def justified_hours
    "#{hours}".rjust(2, "0")
  end

  def justified_minutes
    "#{clock_minutes}".rjust(2, "0")
  end

  def clock_minutes
    minutes % 60
  end
end
