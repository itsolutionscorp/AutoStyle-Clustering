class Bst
  attr_accessor :left, :right
  attr_reader :data
  
  def initialize value
    @data = value
  end

  def insert value
    value <= @data ? insert_left(value) : insert_right(value)
  end

  def each &block
    @left.each{ |y| yield y } if @left
    yield @data
    @right.each { |y| yield y } if @right
  end

private
  def insert_left value
    @left ? @left.insert(value) : @left = Bst.new(value)
  end

  def insert_right value
    @right ? @right.insert(value) : @right = Bst.new(value)
  end
end
