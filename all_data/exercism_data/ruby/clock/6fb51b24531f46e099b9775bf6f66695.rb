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
    self + -min
  end

  def ==(other)
    @hrs == other.hrs && @min == other.min
  end

  def to_s
    "%02d:%02d" % [ @hrs, @min ]
  end

end
