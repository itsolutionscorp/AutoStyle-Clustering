class Clock
  def self.at(*args)
    new(*args)
  end

  def initialize(hour, minute = 0)
    @time = Time.new(0, 1, 1, hour, minute)
  end

  def to_s
    time.strftime('%H:%M')
  end

  def +(minutes)
    self.time += (minutes * 60)
    self
  end

  def -(minutes)
    self.time -= (minutes * 60)
    self
  end

  def ==(clock)
    to_s == clock.to_s
  end

  private

  attr_accessor :time
end
