class Bst
  include Enumerable

  attr_reader :data, :left, :right

  def initialize(data)
    @data  = data
    @left  = EmptyBst.new
    @right = EmptyBst.new
  end

  def insert(value)
    value > data ? insert_right(value) : insert_left(value)
  end

  def each(&block)
    to_a.each(&block)
  end

  protected

  def to_a
    left.to_a + [data] + right.to_a
  end

  private

  attr_writer :left, :right

  def insert_left(value)
    left.insert(value) or 
    self.left = Bst.new(value)
  end

  def insert_right(value)
    right.insert(value) or 
    self.right = Bst.new(value)
  end
end

class EmptyBst
  def data
    nil
  end

  def insert(*)
    false
  end

  def to_a
    []
  end
end
