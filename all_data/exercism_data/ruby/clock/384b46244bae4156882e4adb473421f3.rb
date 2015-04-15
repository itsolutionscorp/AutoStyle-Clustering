class Clock
  attr_accessor :time

  def self.at(*ints)
    self.new Time.new(1, 1, 1, *ints)
  end

  def initialize(time)
    @time = time
  end

  def +(mins)
    self.time += (60 * mins)
    self
  end

  def -(mins)
    self.time -= (60 * mins)
    self
  end

  def ==(other_clock)
    self.to_s == other_clock.to_s
  end

  def to_s
    self.time.strftime("%H:%M")
  end
end
