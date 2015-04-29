class Clock
  def self.at(*parts)
    hours = parts.fetch(0, 0)
    minutes = parts.fetch(1, 0)
    Clock.new(hours, minutes)
  end

  MINUTES_PER_DAY = 1440

  attr_reader :total_minutes

  def initialize(hours, minutes)
    @total_minutes = hours * 60 + minutes
  end

  def hours
    total_minutes / 60
  end

  def minutes
    total_minutes - hours * 60
  end

  def total_minutes=(num)
    @total_minutes = num
    @total_minutes -= MINUTES_PER_DAY while total_minutes > MINUTES_PER_DAY
    @total_minutes += MINUTES_PER_DAY while total_minutes < 0
  end

  def +(mins)
    self.total_minutes += mins
    self
  end

  def -(mins)
    self.total_minutes -= mins
    self
  end

  def ==(other)
    total_minutes == other.total_minutes
  end

  def to_s
    format('%02d:%02d', hours, minutes)
  end
end
