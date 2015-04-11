class Bst
  attr_reader :data, :left, :right
  def initialize(data)
    @data = data
  end

  def insert(data)
    data > @data ? set_right(data) : set_left(data)
  end

  def set_right(data)
    @right ? @right.insert(data) : @right = Bst.new(data)
  end

  def set_left(data)
    @left ? @left.insert(data) : @left = Bst.new(data)
  end

  def each(&block)
    @left.each(&block) if @left
    block.call(data)
    @right.each(&block) if @right
  end
end
