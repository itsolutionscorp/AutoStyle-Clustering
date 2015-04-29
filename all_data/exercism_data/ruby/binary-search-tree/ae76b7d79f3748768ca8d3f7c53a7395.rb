class Bst

  attr_reader :left, :right, :data

  def initialize(data)
    @data = data
  end

  def insert(new_data)
    new_data <= data ? insert_left(new_data) : insert_right(new_data)
  end

  def each(&block)
    @left.each(&block) if @left
    yield data
    @right.each(&block) if @right
  end

private

  def insert_left(new_data)
    @left ? @left.insert(new_data) : @left = self.class.new(new_data)
  end

  def insert_right(new_data)
    @right ? @right.insert(new_data) : @right = self.class.new(new_data)
  end
end
