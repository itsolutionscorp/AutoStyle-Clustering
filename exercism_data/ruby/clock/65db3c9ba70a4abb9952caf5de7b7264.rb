class Clock

  attr_reader :minutes

  def self.at hour, minute = 0
    Clock.new (hour * 60 + minute) % 1440
  end

  def initialize minutes
    @minutes = minutes
  end

  def == clock
    minutes == clock.minutes
  end

  def to_s
    clock_face(@minutes / 60) << ':' << clock_face(@minutes % 60)
  end

  def + num
    @minutes += num %= 1440
    self
  end

  def - num
    @minutes += num %= 1440
    self
  end

  private
    def clock_face minutes
      minutes < 10 ? "0" << minutes.to_s : minutes.to_s
    end

end
