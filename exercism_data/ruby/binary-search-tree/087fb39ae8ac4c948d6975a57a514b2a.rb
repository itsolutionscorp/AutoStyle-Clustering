class Bst
  attr_accessor :data, :left, :right
  private :data=, :left=, :right=

  include Enumerable

  def initialize num
    self.data = num
    self.left = nil
    self.right = nil
  end

  def insert num
    num > data ? insert_right(num) : insert_left(num)
  end

  def each &block
    left.each &block if left
    yield data
    right.each &block if right
  end

  private

  def insert_right num
    right ? self.right.insert(num) : self.right = Bst.new(num)
  end

  def insert_left num
    left ? self.left.insert(num) : self.left = Bst.new(num)
  end
end
