class Bst
  attr_reader :root_node
  def initialize(root_node_value)
    @root_node = Node.new(root_node_value)
  end

  def data
    root_node.data
  end

  def insert(value)
    root_node.insert(value)
  end

  def left
    root_node.left
  end

  def right
    root_node.right
  end

  def each(&block)
    root_node.to_a.each(&block)
  end

  class Node
    attr_accessor :left, :right
    attr_reader :data

    def initialize(data)
      @data = data
    end

    def insert(new_value)
      new_value <= data ? insert_left(new_value) : insert_right(new_value)
    end

    def to_a
      [left.to_a, data, right.to_a].flatten.compact
    end

    private

    def insert_left(new_value)
      if left
        left.insert(new_value)
      else
        @left = Node.new(new_value)
      end
    end

    def insert_right(new_value)
      if right
        right.insert(new_value)
      else
        @right = Node.new(new_value)
      end
    end
  end
end
