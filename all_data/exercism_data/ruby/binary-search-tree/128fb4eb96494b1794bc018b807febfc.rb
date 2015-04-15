class Bst
  include Enumerable

  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    if new_data <= data
      @left = insert_on(left, new_data)
    else
      @right = insert_on(right, new_data)
    end
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private

  def insert_on(subtree, new_data)
    if subtree
      subtree.insert(new_data)
      subtree
    else
      self.class.new(new_data)
    end
  end
end
