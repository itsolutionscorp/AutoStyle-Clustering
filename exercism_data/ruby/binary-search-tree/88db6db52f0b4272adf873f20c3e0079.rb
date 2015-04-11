class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    if new_data > data
      self.right = new_data
    else
      self.left = new_data
    end
  end

  def each(&block)
    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end

  private

  def left=(new_data)
    if left
      left.insert(new_data)
    else
      @left = self.class.new(new_data)
    end
  end

  def right=(new_data)
    if right
      right.insert(new_data)
    else
      @right = self.class.new(new_data)
    end
  end
end
