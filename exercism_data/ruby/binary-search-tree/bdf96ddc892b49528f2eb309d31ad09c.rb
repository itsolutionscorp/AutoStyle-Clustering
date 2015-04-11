# Class Bst - solution to exercism.io binary-search-tree
class Bst
  attr_reader :data, :left, :right

  def initialize(value)
    @data = value
    @left = nil
    @right = nil
  end

  def each
    @left.each { |value| yield value } if @left
    yield @data
    @right.each { |value| yield value } if @right
  end

  def insert(value)
    if value <= @data
      @left = _insert(@left, value)
    else
      @right = _insert(@right, value)
    end
  end

  private

  def _insert(original, value)
    if original
      original.insert(value)
    else
      original = Bst.new(value)
    end
    original
  end
end
