class Node
  attr_accessor :data

  def initialize(input)
    @data = input
  end

  def insert(*)
    nil
  end

end

class Bst
  attr_accessor :data, :left, :right

  def initialize(input)
    @data = input
    @left = Node.new(nil)
    @right = Node.new(nil)
  end

  def insert(input)
    if input <= data
      left.insert(input) || self.left = Bst.new(input)
    else
      right.insert(input) || self.right = Bst.new(input)
    end
  end

  def each(&block)
    left.each(&block) unless left.is_a?(Node)
    yield data
    right.each(&block) unless right.is_a?(Node)
  end

end
