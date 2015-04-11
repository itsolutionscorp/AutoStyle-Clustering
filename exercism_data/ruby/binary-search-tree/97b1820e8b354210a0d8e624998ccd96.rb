class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    if node = send(insert_direction(new_data))
      node.insert(new_data)
    else
      send("#{insert_direction(new_data)}=", self.class.new(new_data))
    end
  end

  def each(&block)
    left.each(&block) if left
    block.call(data)
    right.each(&block) if right
  end

  private

  attr_writer :left, :right

  def insert_direction(new_data)
    if new_data <= data
      :left
    else
      :right
    end
  end
end
