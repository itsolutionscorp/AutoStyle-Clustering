class Bst
  attr_reader :data, :left, :right

  def initialize(n)
    @data = n
    @left = nil
    @right = nil
  end

  def insert(m)
    if m > data
      right ? right.insert(m) : @right = Bst.new(m)
    else
      left ? left.insert(m) : @left = Bst.new(m)
    end
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end
end
