class Bst
  attr_reader :data, :left, :right

  def initialize data
    @data = data
  end

  def insert num
    if num <= data
      !left ? @left = Bst.new(num) : left.insert(num)
    else
      !right ? @right = Bst.new(num) : right.insert(num)
    end
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

end
