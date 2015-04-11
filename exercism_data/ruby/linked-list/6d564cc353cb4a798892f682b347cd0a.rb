class Element
  attr_writer :left, :right

  def initialize(datum, left, right)
    @datum, @left, @right = datum, left, right
    left.right = right.left = self
  end

  def remove
    @right.left, @left.right = @left, @right
    @datum
  end
end

class Deque
  attr_writer :left, :right

  def initialize
    @left = @right = self
  end

  def push(datum)
    Element.new(datum, self, @right)
  end

  def unshift(datum)
    Element.new(datum, @left, self)
  end

  def pop
    @right.remove
  end

  def shift
    @left.remove
  end

  protected def remove; end
end
