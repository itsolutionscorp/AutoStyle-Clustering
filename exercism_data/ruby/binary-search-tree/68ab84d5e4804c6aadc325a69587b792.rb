class Bst
  attr_reader :node_value
  attr_accessor :left_value, :right_value
  def initialize(num)
    @node_value = num
  end

  def data
    node_value
  end

  def left
    left_value || nil
  end

  def right
    right_value || nil
  end

  def insert(num)
    if num <= node_value
      if @left_value
        left_value.insert(num)
      else
        @left_value = Bst.new(num)
      end
    else
      if @right_value
        right_value.insert(num)
      else
        @right_value = Bst.new(num)
      end
    end
  end

  def each
    read_tree.each do |node|
      yield node
    end
  end

  def read_tree
    left_tree = left.read_tree if left
    right_tree = right.read_tree if right
    [left_tree, node_value, right_tree].flatten.compact
  end
end
