class Bst
  attr_reader :left, :right, :data
  def initialize(data)
    @data = data
  end

  def tree
    left_data = @left ? @left.tree : []
    right_data = @right ? @right.tree : []
    left_data + [@data] + right_data
  end

  def insert(n)
    n > @data ? insert_right(n) : insert_left(n)
  end

  def each
    tree.each { |e| yield e }
  end

  private

  def insert_left(n)
    @left ? @left.insert(n) : @left = Bst.new(n)
  end

  def insert_right(n)
    @right ? @right.insert(n) : @right = Bst.new(n)
  end
end
