class Clock
  attr_reader :hours, :minutes

  def self.at(hours, minutes = 0)
    new(hours, minutes)
  end

  def initialize(hours, minutes)
    @hours = hours
    @minutes = minutes
  end

  def +(minutes)
    @minutes += minutes
    until @minutes < 60
      @minutes -= 60
      @hours += 1
      @hours -= 24 if @hours >= 24
    end
    self
  end

  def -(minutes)
    @minutes -= minutes
    until @minutes > 0
      @minutes += 60
      @hours -= 1
      @hours += 24 if @hours < 0
    end
    self
  end

  def ==(clock)
    @hours == clock.hours && @minutes == clock.minutes
  end

  def to_s
    "#{@hours.to_s.rjust(2, '0')}:#{@minutes.to_s.rjust(2, '0')}"
  end
end
