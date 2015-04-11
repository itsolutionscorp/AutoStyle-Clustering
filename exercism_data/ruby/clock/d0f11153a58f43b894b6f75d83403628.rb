class Clock
  HOURS_IN_DAY    = 24
  MINUTES_IN_HOUR = 60

  def initialize(hour, min)
    @hour = Hour.new(hour)
    @min = Minute.new(min)
  end

  def +(minutes)
    @hour += full_hours_in minutes
    @min += remaining minutes
    self
  end

  def -(minutes)
    @hour -= partial_hours_in minutes
    @min -= remaining minutes
    self
  end

  def ==(time)
    time.class == self.class && time.to_s == self.to_s
  end

  def to_s
    "#{@hour}:#{sprintf('%.2d', @min)}"
  end

  def full_hours_in(minutes)
    (minutes + @min.to_i) / MINUTES_IN_HOUR
  end

  def partial_hours_in(minutes)
    ((minutes - @min.to_i) / MINUTES_IN_HOUR) + 1
  end

  def remaining(minutes)
    minutes % MINUTES_IN_HOUR
  end

  def self.at(hour, min=0)
    new(hour, min)
  end

  class MyNum
    def initialize(val, max)
      @val = val
      @max = max 
    end

    def to_s
      sprintf('%.2d', @val)
    end

    def to_i
      @val
    end

    def +(time)
      @val += time 
      @val %= @max
      self
    end

    def -(time)
      @val -= time
      @val %= @max 
      self
    end
  end

  class Hour < MyNum
    def initialize(val)
      super(val, HOURS_IN_DAY)
    end
  end

  class Minute < MyNum
    def initialize(val)
      super(val, MINUTES_IN_HOUR)
    end
  end

end
