class Bst
  attr_reader :data

  def initialize(data)
    @data = data
  end

  def insert(i)
    if i > @data
      self.right = i
    else
      self.left = i
    end
  end

  def left
    @left
  end

  def right
    @right
  end

  def each
    rec(self).each { |i| yield i }
  end

  private
  def rec(root)
    res = []
    return res if root.nil?
    res << root.data
    return res if root.left.nil? and root.right.nil?
    res = rec(root.left) + res + rec(root.right)
    res
  end

  def left=(i)
    if @left.nil?
      @left = Bst.new(i)
    else
      @left.insert(i)
    end
  end

  def right=(i)
    if @right.nil?
      @right = Bst.new(i)
    else
      @right.insert(i)
    end
  end
end
