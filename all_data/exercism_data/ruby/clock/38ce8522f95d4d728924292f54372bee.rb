class Clock
  def self.at(hour, minutes = 0)
    ClockTime.new(hour, minutes)
  end
end

class ClockTime
  attr_reader :hour, :minutes

  def initialize(hour, minutes)
    @hour = ClockTimeParser.scope_hour(hour)
    @minutes = ClockTimeParser.scope_minutes(minutes, self)
  end

  def to_s
    "%s:%s" % ClockTimeParser.normalized_time(hour, minutes)
  end

  def +(addend)
    ClockTime.new(hour, (minutes.to_i + addend)).to_s
  end

  def -(subtrahend)
    ClockTime.new(hour, (minutes.to_i - subtrahend)).to_s
  end

  def ==(other)
    hour == other.hour && minutes == other.minutes
  end

  def adjust_hour(direction)
    raise "Invalid direction" unless valid_directions.include?(direction)
    hour_adjustments[direction].call
  end

  private

  def valid_directions
    hour_adjustments.keys
  end

  def hour_adjustments
    {
      :up => -> { @hour = ClockTimeParser.scope_hour(@hour + 1) },
      :down => -> { @hour = ClockTimeParser.scope_hour(@hour - 1) }
    }
  end

end

class ClockTimeParser
  class << self

    def pad(digit)
      digit.length == 1 ? ("0" + digit) : digit
    end

    def normalized_time(hour, minutes)
      [pad(hour.to_s), pad(minutes.to_s)]
    end

    def scope_hour(hour)
      if hour > 23
        hour -= 24
      elsif hour < 0
        hour += 24
      else
        hour
      end
    end

    def scope_minutes(minutes, clock_time)
      if minutes > 59 
        wrap_up_minutes(minutes, clock_time)
      elsif minutes < 0
        wrap_down_minutes(minutes, clock_time)
      else
        minutes
      end
    end

    def wrap_up_minutes(minutes, clock_time)
      while minutes > 59
        clock_time.send(:adjust_hour, :up)
        minutes -= 60
      end
      minutes
    end

    def wrap_down_minutes(minutes, clock_time)
      while minutes < 0
        clock_time.send(:adjust_hour, :down)
        minutes += 60
      end
      minutes
    end

  end
end
