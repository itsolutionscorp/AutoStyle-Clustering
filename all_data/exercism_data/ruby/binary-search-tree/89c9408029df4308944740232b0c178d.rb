class Bst
  attr_accessor :data, :left, :right

  def initialize(data)
    @data = data
  end

  def each
    left.each { |node| yield node } unless left.nil?
    yield data
    right.each { |node| yield node } unless right.nil?
  end

  def insert(new_data)
    new_data <= data ? insert_left(new_data) : insert_right(new_data)
  end

  private

  def insert_left(data)
    if left.nil?
      @left = self.class.new(data)
    else
      left.insert(data)
    end
  end

  def insert_right(data)
    if right.nil?
      @right = self.class.new(data)
    else
      right.insert(data)
    end
  end
end
