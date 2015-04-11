class Bst
  attr_reader :left, :right, :data

  def initialize(n)
    @data = n
  end

  def insert(n)
    if n <= data
      left && left.insert(n) or @left = Bst.new(n)
    else
      right && right.insert(n) or @right = Bst.new(n)
    end
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end
end
