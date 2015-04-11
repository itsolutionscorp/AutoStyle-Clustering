class Bst
  def initialize head
    @node = head
    @left = nil
    @right = nil
  end

  # def initialize data
  #   @data = data
  #   @left = nil
  #   @right = nil
  # end
  def data
    @node
  end

  def left
    @left
  end

  def right
    @right
  end

  def insert data
    if data <= @node
      @left.nil? ? @left = Bst.new(data) : @left.insert(data)
    else data > @node
      @right.nil? ? @right = Bst.new(data) : @right.insert(data)
    end
  end

  def each &block
    iterate self, &block
  end

  def iterate node, &block
    current = node
    iterate current.left, &block if current.left != nil
    yield current.data
    iterate current.right, &block if current.right != nil
  end
end
