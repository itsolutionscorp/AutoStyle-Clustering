class Bst

  class Node
    attr_accessor :data, :left, :right, :parent

    def initialize(data)
      @data = data
      @left = nil
      @right = nil
    end

    def insert(data)
      if data <= @data
        @left.nil?  ? @left  = Node.new(data) : @left.insert(data)
      else
        @right.nil? ? @right = Node.new(data) : @right.insert(data)
      end
    end
  end

  def initialize(data)
    @root = Node.new data
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

  def insert(data)
    @root.insert(data)

    self
  end

  def each(node = @root, &block)
    return if node.nil?
    each(node.left,  &block)
    yield node.data
    each(node.right, &block)
  end
end
