class Bst
  attr_reader :data, :left, :right

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    if  data < new_data
      insert_new_data(new_data, :right)
    else
      insert_new_data(new_data, :left)
    end
  end

  def each(&block)
    @left.each(&block) if @left
    yield data
    @right.each(&block) if @right
  end

  private

  def insert_new_data(new_data, side)
    if send(side)
      send(side).insert(new_data)
    else
      instance_variable_set("@#{side}", self.class.new(new_data))
    end
  end
end
