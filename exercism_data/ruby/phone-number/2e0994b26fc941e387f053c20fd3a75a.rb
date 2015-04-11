require "scanf"

class PhoneNumber
  attr_accessor :s

  def initialize s
    self.s = s
  end

  def area_code
    number[0, 3]
  end

  def normalize
    n = s.gsub(/\W/, "")
    n = n.sub(/^1/, "") unless n.length == 10
    n = "0000000000"    unless n =~ /^\d{10}$/
    n
  end

  def number
    @number ||= normalize
  end

  def to_s
    "(%d) %d-%d" % number.scanf("%3d%3d%4d")
  end
end
