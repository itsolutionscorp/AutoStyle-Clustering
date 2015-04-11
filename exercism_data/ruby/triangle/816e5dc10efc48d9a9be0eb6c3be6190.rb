module Polygon
  attr_reader :lengths

  def initialize(*lengths)
    @lengths = lengths
    throw_error unless valid? && lengths.count == sides
  end

  def equal_sides
    sides_equal = lengths.count - lengths.uniq.count + 1
    sides_equal == 1 ? 0 : sides_equal
  end

  def equilateral?
    equal_sides == sides
  end

  def valid?
    fail NotImplementedError
  end

  def sides
    fail NotImplementedError
  end

  def self.included(base)
    base.class_eval "class ::#{base}Error < StandardError; end"
    base.class_eval "def throw_error; fail #{base}Error end"
  end
end

class Triangle
  include Polygon

  def sides
    3
  end

  def valid?
    x, y, z = lengths
    x + y > z && x + z > y && z + y > x
  end

  def kind
    @kind ||= [:equilateral, :isosceles, :scalene].each do |type|
      break type if send("#{type}?")
    end
  end

  def isosceles?
    equal_sides == 2
  end

  def scalene?
    equal_sides == 0
  end
end
