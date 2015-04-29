require 'pry'
class Clock

  def initialize h, m
    @minutes = h*60 + m
  end

  def to_s
    "%02d:%02d" % [(@minutes/60)%24, @minutes%60]
  end

  def self.at(h, m=0)
    Clock.new h, m
  end

  def +(rhs)
    @minutes += rhs
    self
  end

  def - (rhs)
    @minutes -= rhs
    self
  end

  def == (rhs)
    to_s == rhs.to_s
  end
end
