class Bst
  class NullNode
    attr_reader :data
    def left; self; end;
    def right; self; end;
    def each; end
    def insert(datum); end
  end

  attr_reader :data, :left, :right
  def initialize datum
    @data = datum
    @left = @right = NullNode.new
  end

  def each &block
    left.each &block
    yield data
    right.each &block
  end

  def insert datum
    if datum > data
      right.insert(datum) || @right = Bst.new(datum)
    else
      left.insert(datum) || @left = Bst.new(datum)
    end
  end
end
