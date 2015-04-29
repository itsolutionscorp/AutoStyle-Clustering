class Clock
  class << self
    def at(hours, minutes=0)
      new(hours, minutes)
    end
  end

  attr_reader :total_minutes

  def initialize(hours, minutes)
    @total_minutes = (hours * 60) + minutes
  end

  def hours
    @total_minutes / 60
  end

  def minutes
    @total_minutes % 60
  end

  def to_s
    [padded_hours, padded_minutes].join(':')
  end

  def +(added_minutes)
    @total_minutes += added_minutes
    wrap_hours
    self
  end

  def -(subtracted_minutes)
    @total_minutes -= subtracted_minutes
    wrap_hours
    self
  end

  def ==(other)
    @total_minutes == other.total_minutes
  end

  private

  def padded_hours
    hours.to_s.rjust(2, '0')
  end

  def padded_minutes
    minutes.to_s.rjust(2, '0')
  end

  def wrap_hours
    if @total_minutes < 0
      @total_minutes = @total_minutes + minutes_in_day
    end
    if @total_minutes > minutes_in_day
      @total_minutes = @total_minutes - minutes_in_day
    end
  end

  def minutes_in_day
    60 * 24
  end
end
