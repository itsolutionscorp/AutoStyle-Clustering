class Clock

  def self.at(hrs, min = 0)
    self.new((hrs * 60) + min)
  end

  attr_reader :min

  def initialize(min)
    @min = min % (24 * 60)
  end

  def +(min)
    return Clock.new(@min + min)
  end

  def -(min)
    self + -min
  end

  def ==(other)
    @min == other.min
  end

  def to_s
    "%02d:%02d" % @min.divmod(60)
  end

end
