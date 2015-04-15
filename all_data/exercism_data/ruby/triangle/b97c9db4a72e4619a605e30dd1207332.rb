class TriangleError < StandardError
end
class Triangle
  def initialize(a, b, c)
    @sides = [a,b,c]
  end

  def kind
    raise TriangleError if invalid_triangle?
    kinds_by_equal_pairs_count[equal_pairs_count]
  end

  attr_reader :sides

  def kinds_by_equal_pairs_count
    { 0 => :scalene, 1 => :isosceles, 3 => :equilateral}
  end

  def equal_pairs_count
    side_pairs.count { |a, b| a == b }
  end

  def side_pairs
    side_rotations.map { |rotation| rotation[0..1] }
  end


  def invalid_triangle?
    sides_have_invalid_size? || triangle_inequality_not_satisified?
  end

  def sides_have_invalid_size?
    sides.any? { |side| side <= 0 }
  end

  def triangle_inequality_not_satisified?
    side_rotations.any? do |a,b,c|
      a + b <= c
    end
  end


  def side_rotations
    sides.map.with_index do |_, rotation_count|
      sides.rotate(rotation_count)
    end
  end

end
