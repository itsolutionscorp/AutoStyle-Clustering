class Bst
  attr_reader :left, :right

  def initialize(value)
    @value = value
  end

  def insert(value)
    if value <= @value
      insert_value(value, :left)
    else
      insert_value(value, :right)
    end
  end

  def data
    @value
  end

  def each(&block)
    left.each(&block) if left
    block.call(@value)
    right.each(&block) if right
  end

  private

  def insert_value(value, side)
    if __send__(side)
      __send__(side).insert(value)
    else
      name = "@#{side}".to_sym
      instance_variable_set(name, self.class.new(value))
    end
  end
end
