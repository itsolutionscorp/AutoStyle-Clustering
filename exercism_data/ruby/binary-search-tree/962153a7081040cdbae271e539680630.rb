class EmptyNode
  attr_accessor :data

  def initialize(data=nil)
    @data = data
  end

  def insert(*)
    false
  end
end

class Bst
  attr_reader :data
  attr_accessor :left, :right

  def initialize(data)
    @data  = data
    @left  = EmptyNode.new
    @right = EmptyNode.new
  end

  def insert(v)
    case data <=> v
    when 1, 0 then insert_left(v)
    when -1 then insert_right(v)
    end
  end

  def each
    left.each {|y| yield y } unless left.kind_of?(EmptyNode)
    yield data
    right.each {|y| yield y } unless right.kind_of?(EmptyNode)
  end

  private
  def insert_left(v)
    left.insert(v) || self.left = Bst.new(v)
  end

  def insert_right(v)
    right.insert(v) || self.right = Bst.new(v)
  end
end
