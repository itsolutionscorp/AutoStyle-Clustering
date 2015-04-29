class Deque
  class Node
    attr_accessor :data, :parent, :child
    def initialize data: nil, parent: nil, child: nil
      @data = data
      @parent = parent
      @child = child
    end
  end
  
  def initialize
    @first = nil
    @last = nil
  end
  
  def push item
    new_node = Node.new data: item, parent: @last
    @last.child = new_node if @last
    @first = new_node unless @first
    @last = new_node
    self
  end
  
  def unshift item
    new_node = Node.new data: item, child: @first
    @first.parent = new_node if @first
    @last = new_node unless @last
    @first = new_node
    self
  end
  
  def pop
    if @last
      pop_node = @last
      @last = pop_node.parent
      @last.child = nil unless @last.nil?
      pop_node.data
    end
  end
  
  def shift
    if @first
      shift_node = @first
      @first = shift_node.child
      @first.parent = nil unless @first.nil?
      shift_node.data
    end
  end
  
end
