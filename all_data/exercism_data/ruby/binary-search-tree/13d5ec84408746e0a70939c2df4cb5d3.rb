class Bst
  include Enumerable

  attr_reader :data
  attr_accessor :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    subtree = self.class.new(new_data)
    if new_data <= data
      left ? left.insert(new_data) : self.left = subtree
    else
      right ? right.insert(new_data) : self.right = subtree
    end
  end

  def each(&block)
    left && left.each(&block)
    yield data
    right && right.each(&block)
  end
end
