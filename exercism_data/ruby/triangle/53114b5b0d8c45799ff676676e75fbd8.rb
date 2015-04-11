class TriangleError < StandardError; end

class Triangle
  
  attr_reader :sides
  def initialize *sides
    @sides = sides.take(3).sort
    raise TriangleError unless valid?
  end
  
  def kind
    @kind ||= %i(equilateral isosceles scalene)[sides.uniq.length-1]
  end
  
  private

  def valid?
    valid_side_permutations.length == 0
  end
  
  def side_permutations
    sides.permutation
  end
  
  def valid_side_permutations
    side_permutations.drop_while{|sides| sides[0] < sides[1]+sides[2]}
  end
end
