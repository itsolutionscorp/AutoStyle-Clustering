class Bst
  attr_reader :data, :left, :right

  def initialize(num)
    @data = num
    @left, @right = nil
  end

  def insert(num)
    node = Bst.new(num)
    num <= data ? add_to(:left, node) : add_to(:right, node)
  end

  def each(&block)
    left.each(&block)  unless left.nil?
    block.call(data)
    right.each(&block) unless right.nil?
  end

  private

  def add_to(side, node)
    send(side) ? send(side).insert(node.data) : instance_variable_set("@#{side}", node)
  end
end
