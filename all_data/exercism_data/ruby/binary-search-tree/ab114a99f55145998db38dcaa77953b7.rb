class Bst
  def initialize(data)
    @data = data
  end

  attr_reader :data, :left, :right

  def insert(new_data)
    if @data < new_data
      insert_to_right new_data
    else
      insert_to_left new_data
    end
  end

  def each
    @left && @left.each { |x| yield x }
    yield @data
    @right && @right.each { |x| yield x }
  end

  private

  def insert_to_left(new_data)
    if @left
      @left.insert new_data
    else
      @left = Bst.new(new_data)
    end
  end

  def insert_to_right(new_data)
    if @right
      @right.insert new_data
    else
      @right = Bst.new(new_data)
    end
  end
end
