class Clock
  attr_reader :hour, :minute

  def initialize(h, m = 0)
    @hour = process_hours(h)
    @minute = process_minutes(m)
  end

  def process_hours(h)
    h -= 24 until h < 24
    h += 24 until h >= 0
    h
  end

  def process_minutes(m)
    q, r = m.divmod 60
    @hour = process_hours(@hour + q)
    r
  end

  def to_s
    "#{@hour.to_s.rjust(2, '0')}:#{@minute.to_s.rjust(2, '0')}"
  end

  def +(other)
    @minute = process_minutes(@minute + other)
    self
  end

  def -(other)
    @minute = process_minutes(@minute - other)
    self
  end

  def ==(other)
    other.hour == hour && other.minute == minute
  end

  def self.at(h, m = 0)
    Clock.new(h, m)
  end
end
