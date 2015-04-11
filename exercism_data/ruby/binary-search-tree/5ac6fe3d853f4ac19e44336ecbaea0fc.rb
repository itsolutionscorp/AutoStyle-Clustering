class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    # Disallow non-comparable objects, like nil.
    fail ArgumentError unless data.respond_to?(:<=)

    @data = data
  end

  def insert(data)
    if data <= self.data
      left_insert(data)
    else
      right_insert(data)
    end

    # Allow chaining of inserts to build up the tree.
    self
  end

  def each(&block)
    return to_enum unless block

    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end

  def inspect
    "#<#{self.class.name}: #{each.entries}>"
  end

  private

  def left_insert(data)
    if left
      left.insert(data)
    else
      @left = self.class.new(data)
    end
  end

  def right_insert(data)
    if right
      right.insert(data)
    else
      @right = self.class.new(data)
    end
  end
end
