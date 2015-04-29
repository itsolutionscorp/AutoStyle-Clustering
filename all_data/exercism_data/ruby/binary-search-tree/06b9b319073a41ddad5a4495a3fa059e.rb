require 'pry'
class Bst
  attr_reader :data
  attr_accessor :right, :left
  def initialize(data, left=nil, right=nil)
    @data = data
    @left = right
    @right = left
  end

  def insert(data)
    if data > self.data
      if self.right == nil
        self.right = Bst.new(data)
      else
        self.right.insert(data)
      end
    else
      if self.left == nil
        self.left = Bst.new(data)
      else
        self.left.insert(data)
      end
    end
  end

  def each(&block)
    if self.left == nil && self.right == nil
      if block_given?
        yield(self.data)
      else
        return self.data
      end
    else
      yield(self.left.each)
    end
  end

end
