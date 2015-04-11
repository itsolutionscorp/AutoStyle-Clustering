class Bst
  attr_accessor :data, :left, :right
  private :data=, :left=, :right=

  def initialize num
    self.data = num
    self.left = nil
    self.right = nil
  end

  def insert num
    num > data ? insert_right(num) : insert_left(num)
  end

  def each &block
    self.to_a.sort.each &block
  end

  def to_a
    array = []

    array << data
    array << left.to_a if left
    array << right.to_a if right

    array.flatten
  end

  private

  def insert_right num
    right ? self.right.insert(num) : self.right = Bst.new(num)
  end

  def insert_left num
    left ? self.left.insert(num) : self.left = Bst.new(num)
  end
end
