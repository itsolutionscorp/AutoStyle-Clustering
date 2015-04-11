class Bst
  attr_reader :data
  attr_accessor :left, :right
  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end
  
  def insert(data)
    return Bst.new(data) if self == nil
    if self.data >= data
      if self.left == nil
        self.left = Bst.new(data)
      else
        left.insert(data)
      end
    else
      if self.right == nil
        self.right = Bst.new(data)
      else
        right.insert(data)
      end
    end
  end
  
  def inorder
    ret = []
    ret << (self.left).inorder if self.left
    ret << self.data
    ret << (self.right).inorder if self.right
    return ret
  end

  def each &block
    self.inorder.flatten.each { |data| block.call(data) }
  end
end
