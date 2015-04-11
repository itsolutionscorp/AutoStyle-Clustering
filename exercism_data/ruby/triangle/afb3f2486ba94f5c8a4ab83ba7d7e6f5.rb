class TriangleError < RuntimeError
end

class Triangle

  attr_accessor :sides

  def initialize(*args)
    @sides = args.map.to_a
    return @sides
  end

  def kind
    case

    when @sides.any? { |arg| arg <= 0}
      raise TriangleError.new

    when @sides.each_with_index.any? do |side, idx|
      sides = @sides.map { |side| side }
      sides.delete_at idx
      side >= sides.inject(:+)
    end
    raise TriangleError.new

    when @sides.uniq.count == 1
      stuff = :equilateral

    when @sides.uniq.count == 2
      stuff = :isosceles

    else
      stuff =  :scalene
   end
  end
end
