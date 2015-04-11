class EmptyBst
  def insert(n)
    Bst.new(n)
  end

  def each(&block) end
end

class Bst
  EMPTY = EmptyBst.new

  attr_reader :left, :right, :data

  def initialize(n)
    @data = n
    @left = @right = EMPTY
  end

  def insert(n)
    n <= data ? @left = @left.insert(n) : @right = @right.insert(n)
    self
  end

  def each(&block)
    left.each(&block)
    yield data
    right.each(&block)
  end
end
