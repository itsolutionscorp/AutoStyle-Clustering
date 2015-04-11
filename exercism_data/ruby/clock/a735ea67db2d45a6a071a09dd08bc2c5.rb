# clock.rb
class Clock
  attr_reader :hour, :minute

  def self.at(hour, minute = 0)
    new(hour, minute)
  end

  def initialize(hour, minute)
    @hour = hour
    @minute = minute
  end

  def +(minutes)
    tap do
      hours, @minute = (minute + minutes).divmod(60)
      @hour = (hour + hours) % 24
    end
  end

  def -(minutes)
    send(:+, -minutes)
  end

  def ==(other)
    to_s == other.to_s
  end

  def to_s
    format('%02i:%02i', hour, minute)
  end
end
