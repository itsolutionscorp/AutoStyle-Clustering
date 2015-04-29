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
    (size - 1).times.inject(1) do |memo, i|
      memo = memo * 2
      memo
    end
  end
end
