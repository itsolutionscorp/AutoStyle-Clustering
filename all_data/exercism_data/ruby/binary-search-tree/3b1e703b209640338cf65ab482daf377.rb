class Node
  attr_accessor :data, :left, :right

  def initialize(data, left = nil, right = nil)
    @data = data
    @left = left
    @right = right
  end
end

class Bst
  def initialize(data)
    @root = Node.new(data)
  end

  def data
    @root.data
  end

  def left
    @root.left
  end

  def right
    @root.right
  end

  def insert(data, current_node = @root)
    if data <= current_node.data
      current_node.left ? insert(data, current_node.left) : current_node.left = Node.new(data)
    else
      current_node.right ? insert(data, current_node.right) : current_node.right = Node.new(data)
    end
  end

  def each(current_node = @root, &block)
    each(current_node.left, &block) if current_node.left
    yield current_node.data
    each(current_node.right, &block) if current_node.right
  end
end
