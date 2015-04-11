class Bst
 
  class Node
    attr_accessor :data, :left, :right

    def initialize(data, left, right)
      @data = data
      @left = left
      @right = right
    end

    def leaf?
      @left == nil && @right == nil
    end
  end

  attr_accessor :root

  def initialize(root_val)
    @root = Node.new(root_val, nil, nil)
  end

  def data(node = @root)
    node.data
  end

  def left(node = @root)
    node.left
  end

  def right(node = @root)
    node.right
  end

  def insert(new_value)
    node = @root
    while node != nil
      if new_value <= node.data 
        if node.left == nil
          node.left = Node.new(new_value, nil, nil)
          return
        else
          node = node.left
        end
      elsif new_value > node.data
        if node.right == nil
          node.right = Node.new(new_value, nil, nil)
          return
        else
          node = node.right
        end
      end
    end
  end

  def each(node = @root, &block)
    if node.leaf?
      block.call node.data
    else
      each(node.left, &block) if node.left
      block.call node.data
      each(node.right, &block) if node.right
    end
  end

end
