class Bst
  attr_reader :data, :left, :right

  def initialize n
    @data = n
    @left = @right = nil
  end

  def insert n
    if n <= data
      left ? left.insert(n) : @left = Bst.new(n)
    else
      right ? right.insert(n) : @right = Bst.new(n)
    end
  end

  def each &block
    left.each &block if left
    yield  data
    right.each &block if right
  end

end
