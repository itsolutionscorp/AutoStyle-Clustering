class Clock

  def self.at(hrs, min = 0)
    self.new(hrs, min)
  end

  attr_reader :hrs, :min

  def initialize(hrs, min)
    @hrs = hrs
    @min = min
  end

  def +(min)
    @min += min % 60
    @hrs =  (@hrs + min / 60) % 24
    return self
  end

  def -(min)
    self + (-1 * min)
  end

  def ==(other)
    @hrs == other.hrs && @min == other.min
  end

  def to_s
    "#{@hrs / 10}#{@hrs % 10}:#{@min / 10}#{@min % 10}"
  end

end
