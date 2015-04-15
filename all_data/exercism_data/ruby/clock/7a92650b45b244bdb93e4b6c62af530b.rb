class Clock
  attr_reader :hour
  attr_reader :min

  def initialize(hr, min = 0)
    @hour = hr
    @min = min
  end

  def self.at(*args)
    Clock.new(*args)
  end

  def ==(other)
    [@hour, @min] == [other.hour, other.min]
  end

  def +(other)
    [].tap do |x|
      x = other.divmod(60)
      @min += x[1]
      @hour += x[0]

      @hour = @hour % 24
    end

    to_s
  end

  def -(other)
    [].tap do |x|
      x = other.divmod(60)
      @min -= x[1]
      @hour -= (x[1] != 0 ? x[0] + 1 : x[0])

      @min = @min.abs
      @hour = @hour % 24
    end

    to_s
  end

  def to_s
    format '%02d:%02d', @hour, @min
  end
end
