class Bst
  attr_reader :data
  attr_accessor :right, :left
  def initialize(data, left=nil, right=nil)
    @data = data
  end

  def insert(new_data)
    if new_data > self.data
      insert_value(new_data, :right)
    else
      insert_value(new_data, :left)
    end
  end

  def each(&block)
    left.each(&block) if left
    yield data
    right.each(&block) if right
  end

  private

  def insert_value(value, side)
    if send(side)
      send(side).insert(value)
    else
      instance_variable_set("@#{side}", self.class.new(value))
    end
  end

end
