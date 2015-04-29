class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(value, bst = self)
    if value <= bst.data
      bst.left ? insert(value, bst.left) : bst.left = Bst.new(value)
    else
      bst.right ? insert(value, bst.right) : bst.right = Bst.new(value)
    end
  end

  def each(bst = self, &block)
    each(bst.left, &block) if bst.left
    block.call(bst.data)
    each(bst.right, &block) if bst.right
  end
end
