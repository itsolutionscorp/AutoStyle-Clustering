class Bst
  attr_reader :data, :left, :right
  def initialize datum
    @data = datum
  end

  def each &block
    left.each &block if left
    yield data
    right.each &block if right
  end

  def insert datum
    if datum > data
      insert_right datum
    else
      insert_left datum
    end
  end

  private

  def insert_left datum
    return left.insert datum if left
    @left = Bst.new datum
  end

  def insert_right datum
    return right.insert datum if right
    @right = Bst.new datum
  end
end
