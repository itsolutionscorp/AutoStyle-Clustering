class Grains
  def square(size)
    Square.new(size).grains
  end

  def total
    (1..64).inject(0) do |memo, i|
      memo += square(i)
      memo
    end
  end
end

class Square
  attr_reader :size

  def initialize(size)
    @size = size
  end

  def grains
    2 ** (size - 1)
  end
end
