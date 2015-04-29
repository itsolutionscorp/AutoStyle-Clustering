class Bst
  attr_reader :data, :left, :right

  def initialize data
    @data   = data
  end

  def insert new_data
    if new_data > data
      right ? right.insert(new_data) : @right = Bst.new(new_data)
    else
      left ? left.insert(new_data) : @left = Bst.new(new_data)
    end
  end

  def each &b
   left.each &b if left
   b.call data
   right.each &b if right
  end
end
