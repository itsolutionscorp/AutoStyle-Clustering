class Bst
  attr_reader :data, :left, :right

  def initialize(data=nil)
    @data = data
    @left, @right = nil, nil
  end

  def insert value
    if value <= @data
      if @left
        @left.insert(value)
      else
        @left = Bst.new(value)
      end
    else
      if @right
        @right.insert(value)
      else
        @right = Bst.new(value)
      end
    end
  end

  def each
    if block_given?
      left.each { |v| yield v } if left
      yield data
      right.each { |v| yield v } if right
    end
  end
end
