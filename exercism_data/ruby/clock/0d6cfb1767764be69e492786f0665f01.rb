class Clock

  attr_accessor :minutes

  def self.at(hour, minutes=0)
    Clock.new(hour, minutes)
  end

  def initialize(hour, minutes)
    @minutes = hour * 60 + minutes
  end

  def to_s
    hours = @minutes/60
    minutes = @minutes % 60

    if hours >= 24
      hours -= 24
    elsif hours <= 0
      hours += 24
    end

    if hours.to_s.length == 1
      hours = "0#{hours}"
    elsif hours.to_s.length == 0
      hours = "00"
    end

    if minutes.to_s.length == 1
      minutes = "0#{minutes}"
    elsif minutes.to_s.length == 0
      minutes = "00"
    end

    "#{hours}:#{minutes}"
  end

  def +(more_minutes)
    @minutes += more_minutes
    self
  end

  def -(more_minutes)
    @minutes -= more_minutes
    self
  end

  def ==(other)
    self.to_s == other.to_s
  end

end
