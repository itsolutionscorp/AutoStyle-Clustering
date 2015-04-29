class Bst
  include Enumerable

  attr_reader :data, :left, :right

  attr_writer :data, :left, :right
  private :data=, :left=, :right=

  def initialize(data)
    self.data = data
  end

  def insert(new_data)
    if new_data <= data
      insert_on(:left, new_data)
    else
      insert_on(:right, new_data)
    end
  end

  def each(&block)
    left && left.each(&block)
    yield data
    right && right.each(&block)
  end

  private

  def insert_on(side, new_data)
    existing_node = public_send(side)

    if existing_node
      existing_node.insert(new_data)
    else
      new_node = self.class.new(new_data)
      self.send("#{side}=", new_node)
    end
  end
end
