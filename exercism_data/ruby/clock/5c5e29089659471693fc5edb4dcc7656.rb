class Clock
  attr_reader :hours, :minutes

  def self.at(hours, minutes = 0)
    return self.new(hours, minutes)
  end

  def initialize(hours, minutes)
    @hours = hours
    @minutes = minutes
  end

  def +(minutes_to_add)
    minutes_to_add = minutes_to_add + @minutes

    hours_to_add, minutes_to_add = minutes_to_add.divmod(60)

    hours = @hours + hours_to_add

    @hours = hours.divmod(24)[1]
    @minutes = minutes_to_add

    self
  end

  def -(minutes_to_subtract)
    hours_to_subtract, minutes_to_subtract = minutes_to_subtract.divmod(60)

    if (@minutes - minutes_to_subtract < 0)
      hours_to_subtract += 1
      @minutes = 60 - minutes_to_subtract + @minutes
    else
      @minutes = @minutes - minutes_to_subtract
    end

    hours = @hours - hours_to_subtract
    @hours = hours.divmod(24)[1]

    self
  end

  def ==(other_clock)
    other_clock.hours == @hours && other_clock.minutes == @minutes
  end

  def to_s
    hours = format_unit(@hours)
    minutes = format_unit(@minutes)

    return "#{hours}:#{minutes}"
  end

  private
  def format_unit(unit)
    unit_string = unit.to_s

    if (unit < 10)
      return "0" + unit_string
    end
    unit_string
  end
end
