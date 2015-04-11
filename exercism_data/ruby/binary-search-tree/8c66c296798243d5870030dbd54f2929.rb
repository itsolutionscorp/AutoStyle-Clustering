class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
    @left = nil
    @right = nil
  end

  def insert(value)
    if value <= data
      insert_to_left(value)
    else
      insert_to_right(value)
    end
  end

  def << (value)
    insert(value)
  end

  def each(&block)
    left && left.each(&block)
    block.call(data)
    right && right.each(&block)
  end


  private

  def insert_to_left(value)
    if left
      left.insert(value)
    else
      @left = Bst.new(value)
    end
  end

  def insert_to_right(value)
    if right
      right.insert(value)
    else
      @right = Bst.new(value)
    end
  end

end
