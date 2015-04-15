class Bst

  attr_reader :left, :right, :data

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    new_data > data ? insert_on(:right, new_data) : insert_on(:left, new_data)
  end

  def each(&block)
    @left.each(&block) if @left
    yield data
    @right.each(&block) if @right
  end

private

  def insert_on(side, new_data)
    send(side) ? send(side).insert(new_data) : instance_variable_set("@#{side}", self.class.new(new_data))
  end

end
