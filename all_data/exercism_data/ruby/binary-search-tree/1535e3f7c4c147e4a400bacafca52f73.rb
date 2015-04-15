class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(value)
    direction = insert_direction(value)

    if leaf = send(direction)
      leaf.insert(value)
    else
      send("#{direction}=", Bst.new(value))
    end
  end

  def insert_direction(value)
    value <= data ? :left : :right
  end

  def each(&block)
    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end
end
