class Clock
  MINUTES_IN_AN_HOUR = 60
  MINUTES_IN_A_DAY = MINUTES_IN_AN_HOUR * 24

  def initialize total_minutes
    @total_minutes = total_minutes
    @hours = @total_minutes / MINUTES_IN_AN_HOUR
    @minutes = @total_minutes % MINUTES_IN_AN_HOUR
  end

  def self.at(hours, minutes = 0)
    Clock.new hours * 60 + minutes
  end

  def to_s
    "#{@hours.to_s.rjust(2, '0')}:#{@minutes.to_s.rjust(2, '0')}"
  end

  def +(minute_delta)
    Clock.new((@total_minutes + minute_delta) % MINUTES_IN_A_DAY)
  end  

  def -(minute_delta)
    self + (-minute_delta)
  end  

  def ==(other)
    to_s == other.to_s
  end
end
