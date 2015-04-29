class Bst
  attr_reader :left
  attr_reader :right
  attr_reader :data

  def initialize data, parent=nil
    @parent = parent
    @data   = data
  end

  def insert data
    if data <= @data then
      if left then
        left.insert data
      else
        @left = Bst.new data, self
      end
    else
      if right then
        right.insert data
      else
        @right = Bst.new data, self
      end
    end
  end

  def each &block
    to_a.map(&:data).each(&block)
  end

  def to_a
    [left ? left.to_a : [], self, right ? right.to_a : []].flatten
  end

end
