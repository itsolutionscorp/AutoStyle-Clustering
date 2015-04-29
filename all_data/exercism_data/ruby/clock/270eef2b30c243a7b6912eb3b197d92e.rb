class Clock
  MINUTES_PER_HOUR = 60
  HOURS_PER_DAY = 24

  attr_reader :hour, :minute

  def initialize(hour, minute = 0)
    additional_hours, @minute = minute.divmod(MINUTES_PER_HOUR)
    @hour = (hour + additional_hours) % HOURS_PER_DAY
  end

  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def +(minutes)
    self.class.new(@hour, @minute + minutes)
  end

  def -(minutes)
    self.class.new(@hour, @minute - minutes)
  end

  def ==(other)
    @hour == other.hour && @minute == other.minute
  end

  def to_s
    [@hour, @minute].map { |item| item.to_s.rjust(2, '0') }.join(':')
  end
end
