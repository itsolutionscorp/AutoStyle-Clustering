class Bst
  attr_reader :data,
              :left,
              :right

  def initialize(data)
    @data = data
  end

  def insert(data)
    data <= self.data ? self.left = data : self.right = data
  end

  def left=(data)
    left ? @left.insert(data) : @left = Bst.new(data)
  end

  def right=(data)
    right ? @right.insert(data) : @right = Bst.new(data)
  end

  def each(&block)
    left && left.each(&block)
    block.call(data)
    right && right.each(&block)
  end
end
