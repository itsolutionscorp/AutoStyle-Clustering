class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(value)
    child = value <= data ? :left : :right
    insert_node(child, value)
  end

  def each(&block)
    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end

  private

  def insert_node(child, value)
    if send(child)
      send(child).insert(value)
    else
      instance_variable_set("@#{child}", self.class.new(value))
    end
  end
end
