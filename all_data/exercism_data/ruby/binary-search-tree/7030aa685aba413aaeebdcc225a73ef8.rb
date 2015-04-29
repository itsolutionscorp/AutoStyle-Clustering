class Bst
  attr_accessor :data
  attr_accessor :left
  attr_accessor :right
  def initialize(data)
    @data = data
  end

  def insert(data)
    data <= self.data ? self.left = data : self.right = data
  end

  def left=(data)
    @left == nil ? @left = Bst.new(data) : @left.insert(data)
  end

  def right=(data)
    @right == nil ? @right = Bst.new(data) : @right.insert(data)
  end

  def each(&block)
    left && left.each(&block)
    block.call(data)
    right && right.each(&block)
  end
end
