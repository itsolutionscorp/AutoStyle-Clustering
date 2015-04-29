# binary search tree implementation
class Bst
  attr_reader :data
  attr_accessor :left, :right

  def initialize(n)
    @data = n
  end

  def insert(n, node = self)
    if n <= node.data
      node.left ? insert(n, node.left) : node.left = Bst.new(n)
    else
      node.right ? insert(n, node.right) : node.right = Bst.new(n)
    end
  end

  def each(root = self, &block)
    inorder_traversal(root, block)
  end

  private

  def inorder_traversal(curr_root, block)
    return unless curr_root
    inorder_traversal(curr_root.left, block)
    block.call curr_root.data
    inorder_traversal(curr_root.right, block)
  end
end
