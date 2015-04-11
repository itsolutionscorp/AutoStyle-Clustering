class Clock

  def initialize minutes
    minutes += 60 * 24 if minutes < 0
    @hours = minutes / 60
    @minutes = minutes % 60
    @hours = @hours - 24 if @hours > 23
  end

  def self.at hours, minutes = 0
    Clock.new hours * 60 + minutes
  end

  def to_s
    "#{@hours.to_s.rjust(2, '0')}:#{@minutes.to_s.rjust(2, '0')}"
  end

  def + minutes
    Clock.new @hours * 60 + @minutes + minutes
  end

  def - minutes
    Clock.new @hours * 60 + @minutes - minutes
  end

end
