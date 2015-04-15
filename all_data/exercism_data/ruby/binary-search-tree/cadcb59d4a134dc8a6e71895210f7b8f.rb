
class Bst
  include Enumerable

  attr_accessor :data, :left, :right
  def initialize(data, left=nil, right=nil)
    @data = data
    @left = left
    @right = right
  end

  def insert(v)
    if v <= data
      left.nil? ? self.left = Bst.new(v) : left.insert(v)
    else
      right.nil? ? self.right = Bst.new(v) : right.insert(v)
    end
  end

  def each
    left.each { |node| yield node } unless left.nil?
    yield self.data
    right.each { |node| yield node } unless right.nil?
  end
end
