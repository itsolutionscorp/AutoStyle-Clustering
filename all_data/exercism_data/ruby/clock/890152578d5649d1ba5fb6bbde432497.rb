class Clock

  def initialize(minutes)
    @minutes = minutes % (24 * 60)
  end

  def self.at(hours, minutes = 0)
    new hours * 60 + minutes
  end

  def +(minutes)
    self.class.new(@minutes + minutes)
  end

  def -(minutes)
    self.class.new(@minutes - minutes)
  end 

  def ==(other)
    other.class == self.class && other.to_s == to_s
  end

  def to_s
    "%02d:%02d" % @minutes.divmod(60)
  end
end
