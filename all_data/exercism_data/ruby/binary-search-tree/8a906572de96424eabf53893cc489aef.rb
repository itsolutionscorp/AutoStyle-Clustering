class Bst
  include Enumerable

  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end

  def insert(data)
    if data <= self.data
      @left = left ? left.insert(data) : self.class.new(data)
    else
      @right = right ? right.insert(data) : self.class.new(data)
    end
    self
  end

  def each(&block)
    left.each(&block) if left
    yield self.data
    right.each(&block) if right
  end
end
