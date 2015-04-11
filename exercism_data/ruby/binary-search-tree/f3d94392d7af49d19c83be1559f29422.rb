class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(value)
    if value <= data
      @left ? @left.insert(value) : @left = Bst.new(value)
    else
      @right ? @right.insert(value) : @right = Bst.new(value)
    end
  end

  def each
    left.each { |value| yield value } if left
    yield data
    right.each { |value| yield value } if right
  end
end
