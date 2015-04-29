class Bst
  attr_reader :left, :right, :data

  def initialize(val)
    self.data=val
  end

  def insert(num)
    num<=data ? self.left=num : self.right=num
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private
  attr_writer :data
  def left=(num)
    self.left ? left.insert(num) : @left=self.class.new(num)
  end

  def right=(num)
    self.right ? right.insert(num) : @right=self.class.new(num)
  end
end
