class Bst

  attr_accessor :root_node, :tmp_node
  BSTNode = Struct.new(:value, :left_node, :right_node)

  def initialize root_val = nil
    @root_node = BSTNode.new(root_val, nil, nil) unless root_val.nil?
    @tmp_node = @root_node unless @root_node.nil?
  end

  def insert value
    new_node = BSTNode.new(value, nil, nil)
    @root_node = new_node and return if @root_node.nil?

    insert_at_node @root_node, new_node
  end

  def insert_at_node tree_node, new_node
    tree_node = new_node and return if tree_node.nil?

    return tree_node if new_node.value == tree_node.value

    if new_node.value < tree_node.value
      return insert_at_node(tree_node.left_node, new_node) unless tree_node.left_node.nil?
      return tree_node.left_node = new_node
    end

    return insert_at_node(tree_node.right_node, new_node) unless tree_node.right_node.nil?
    return tree_node.right_node = new_node
  end

  def data 
    node = @tmp_node
    @tmp_node = root_node
    data_at_node node
  end

  def data_at_node node
    return if node.nil?
    node.value
  end

  def left
    @tmp_node = tmp_node.left_node unless tmp_node.left_node.nil?
    self
  end
    
  def right
    @tmp_node = tmp_node.right_node unless tmp_node.right_node.nil?
    self
  end

  def each &block
    traverse_at_node root_node, &block
  end

  def traverse_at_node node, &block
    return if node.nil?
    traverse_at_node(node.left_node, &block)
    yield node.value if block_given?
    traverse_at_node(node.right_node, &block)
  end

end
