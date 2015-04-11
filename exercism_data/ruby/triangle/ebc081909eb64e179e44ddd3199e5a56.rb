class TriangleError < StandardError; end

class Triangle
  NUMBER_OF_SIDES = 3

  class << self
    def valid_triangle?(sides)
       valid_number_of_sides(sides) && valid_dimenions?(sides) && valid_mathemtical_dimensions?(sides)
    end
    
    def valid_dimenions?(sides)
      sides.count { |side| side > 0 } == NUMBER_OF_SIDES
    end

    def valid_mathemtical_dimensions?(sides)
      sides.sort!
      (sides[0] + sides[1]) > sides[2]
    end

    def valid_number_of_sides(sides)
      sides.size == NUMBER_OF_SIDES
    end
  end

  attr_reader :sides
  def initialize(*sides)
    raise TriangleError unless self.class.valid_triangle?(sides)
    @sides = sides
  end

  def kind
    @kind ||= calculate_kind
  end

  private

  def number_of_unique_sides
    sides.uniq.length
  end

  def calculate_kind
    if (number_of_unique_sides == 1)
      :equilateral
    elsif (number_of_unique_sides == 2)
      :isosceles
    else
      :scalene
    end
  end
end
