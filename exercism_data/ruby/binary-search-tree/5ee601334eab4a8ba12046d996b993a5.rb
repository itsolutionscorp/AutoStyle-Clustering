class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data 
    @left = nil
    @right = nil
  end

  def insert(number)
    node = traverse(self, number)
    number > node.data ? node.right = Bst.new(number) : node.left = Bst.new(number)
  end

  def each(&block)
    collect_all(self).each {|data| block.call(data)}
  end
  
  private

  def traverse(node, number)
    if number > node.data
      return node if node.right.nil?
      traverse(node.right, number)
    else
      return node if node.left.nil?
      traverse(node.left, number)
    end
  end

  def collect_all(node)
    return [] if node.nil?

    return collect_all(node.left) + [node.data] + collect_all(node.right)
  end


end
