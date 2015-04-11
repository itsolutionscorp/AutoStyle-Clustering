class Bst

  def initialize(base)
    @tree = Hash.new
    @tree["b"] = base
    @treeIndex = "b"
  end

  def insert(n)
    place = "b"
    while @tree.has_key?(place)
      if n > @tree[place]
        place << 'r'
      else
        place << 'l'
      end
    end
    @tree[place] = n
  end

  def data
    result = @tree[@treeIndex]
    @treeIndex = "b"
    result
  end

  def left
    @treeIndex << "l"
    return self
  end

  def right
    @treeIndex << "r"
    return self
  end

  def each
    i = 0
    t = @tree.values.sort
    while i < @tree.length
      yield t[i]
      i += 1
    end
  end
end
